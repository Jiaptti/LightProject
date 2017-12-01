package com.viroyal.light.config;

import com.viroyal.light.module.entity.SysUser;
import com.viroyal.light.module.service.impl.SysUserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 *在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。
 * 因为在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。
 * 通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。
 * 可以说，Realm是专用于安全框架的DAO.
 *
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserServiceImpl sysUserService;

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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", token.getUsername());
        map.put("password", token.getPassword());
        List<SysUser> userList = sysUserService.selectByMap(map);
        SysUser user = null;
        if(userList.size()!=0){
            user = userList.get(0);
        }
        if(user == null){
            throw new AccountException("帐号或密码不正确！");
        } else if(user.getStatus().equals("0")){
            throw new DisabledAccountException("帐号已经禁止登录！");
        }else{
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            sysUserService.updateById(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPswd(), getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }
}
