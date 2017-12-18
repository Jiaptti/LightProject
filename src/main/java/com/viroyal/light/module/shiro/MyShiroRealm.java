package com.viroyal.light.module.shiro;

import com.viroyal.light.module.dao.user.SysPermissionMapper;
import com.viroyal.light.module.dao.user.SysUserMapper;
import com.viroyal.light.module.entity.user.SysPermission;
import com.viroyal.light.module.entity.user.SysRole;
import com.viroyal.light.module.entity.user.SysUser;
import com.viroyal.light.module.service.user.ISysPermissionService;
import com.viroyal.light.module.service.user.ISysRoleService;
import com.viroyal.light.module.service.user.ISysUserService;
import com.viroyal.light.utils.MyDES;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;


/***
 *在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。
 * 因为在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。
 * 通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。
 * 可以说，Realm是专用于安全框架的DAO.
 *
 */

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    SysPermissionMapper permissionMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 1、检查提交的进行认证的令牌信息

     2、根据令牌信息从数据源(通常为数据库)中获取用户信息

     3、对用户信息进行匹配验证。

     4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。

     5、验证失败则抛出AuthenticationException异常信息。

     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
        //计数大于5时，设置用户被锁定一小时
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+name))>=5){
            opsForValue.set(SHIRO_IS_LOCK+name, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+name, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+name))){
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", name);
        //密码进行加密处理  明文为  password+name
        String paw = password+name;
        String pawDES = MyDES.encryptBasedDes(paw);
        map.put("pswd", pawDES);
        SysUser user = null;
        // 从数据库获取对应用户名密码的用户
        List<SysUser> userList = sysUserMapper.selectByMap(map);
        if(userList.size()!=0){
            user = userList.get(0);
        }
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        }else if("0".equals(user.getStatus())){
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        }else{
            //登录成功
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            sysUserMapper.updateById(user);
            //清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT+name, "0");
        }
        Logger.getLogger(getClass()).info("身份认证成功，登录用户："+name);
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //用来做授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        List<String> permsList = null;
        //系统管理员，拥有最高权限
        if(user.getId() == 1){
            List<SysPermission> permissionList = permissionMapper.selectByMap(new HashMap<String, Object>());
            permsList = new ArrayList<>(permissionList.size());
            for(SysPermission permission : permissionList){
                permsList.add(permission.getPerms());
            }
        } else{
            permsList = sysUserMapper.queryAllPerms(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        //添加所有权限
        info.setStringPermissions(permsSet);
        return info;
    }
}
