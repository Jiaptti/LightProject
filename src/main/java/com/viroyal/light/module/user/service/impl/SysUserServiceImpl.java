package com.viroyal.light.module.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.utils.MyDES;
import com.viroyal.light.common.utils.ShiroUtils;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.module.user.entity.SysUser;
import com.viroyal.light.module.user.dao.SysUserMapper;
import com.viroyal.light.module.user.entity.SysUserRole;
import com.viroyal.light.module.user.entity.UserOnlineBo;
import com.viroyal.light.module.user.service.ISysRoleService;
import com.viroyal.light.module.user.service.ISysUserRoleService;
import com.viroyal.light.module.user.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    RedisSessionDAO redisSessionDAO;

    @Autowired
    SessionManager sessionManager;

    //用户服务
    @Autowired
    ISysUserService sysUserService;
    //用户角色服务
    @Autowired
    ISysUserRoleService sysUserRoleService;
    //角色服务
    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public Page<UserOnlineBo> getPagePlus(FrontPage<UserOnlineBo> frontPage) {
        // 因为我们是用redis实现了shiro的session的Dao,而且是采用了shiro+redis这个插件
        // 所以从spring容器中获取redisSessionDAO
        // 来获取session列表.
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Iterator<Session> it = sessions.iterator();
        List<UserOnlineBo> onlineUserList = new ArrayList<UserOnlineBo>();
        Page<UserOnlineBo> pageList = frontPage.getPagePlus();
        // 遍历session
        while (it.hasNext()) {
            // 这是shiro已经存入session的
            // 现在直接取就是了
            Session session = it.next();
            if(session.getAttribute("kickout")==null?false:true)continue;
            UserOnlineBo onlineUser = getSessionBo(session);
            if(onlineUser!=null){
                onlineUserList.add(onlineUser);
            }
        }
        // 再将List<UserOnlineBo>转换成mybatisPlus封装的page对象
        int page = frontPage.getPage() - 1;
        int rows = frontPage.getRows() - 1;
        int startIndex = page * rows;
        int endIndex = (page * rows) + rows;
        int size = onlineUserList.size();
        if (endIndex > size) {
            endIndex = size;
        }
        pageList.setRecords(onlineUserList.subList(startIndex, endIndex));
        pageList.setTotal(size);
        return pageList;
    }

    //从session中获取UserOnline对象
    private UserOnlineBo getSessionBo(Session session){
        //获取session登录信息。
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if(null == obj){
            return null;
        }
        //确保是 SimplePrincipalCollection对象。
        if(obj instanceof SimplePrincipalCollection){
            SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
            /**
             * 获取用户登录的，@link SampleRealm.doGetAuthenticationInfo(...)方法中
             * return new SimpleAuthenticationInfo(user,user.getPswd(), getName());的user 对象。
             */
            obj = spc.getPrimaryPrincipal();
            if(null != obj && obj instanceof SysUser){
                //存储session + user 综合信息
                UserOnlineBo userBo = new UserOnlineBo((SysUser)obj);
                //最后一次和系统交互的时间
                userBo.setLastAccess(session.getLastAccessTime());
                //主机的ip地址
                userBo.setHost(session.getHost());
                //session ID
                userBo.setSessionId(session.getId().toString());
                //session最后一次与系统交互的时间
                userBo.setLastLoginTime(session.getLastAccessTime());
                //回话到期 ttl(ms)
                userBo.setTimeout(session.getTimeout());
                //session创建时间
                userBo.setStartTime(session.getStartTimestamp());
                //是否踢出
                userBo.setSessionStatus(false);
                return userBo;
            }
        }
        return null;
    }

    //根据sessionId执行强制退出
    @Transactional
    @Override
    public void kickout(Serializable sessionId){
        SysUser user =  sysUserMapper.selectById(sessionId);
        user.setLastLoginTime(new Date());
        sysUserMapper.update(user);
        this.getSessionBysessionId(sessionId).setAttribute("kickout", true);
    }

    @Transactional
    @Override
    public void logout(Serializable sessionId) {
        SysUser user = sysUserMapper.selectById(sessionId);
        user.setLastLoginTime(new Date());
        sysUserMapper.update(user);
    }

    @Transactional
    @Override
    public void saveUser(SysUser user, String isEffective) {
        user.setCreateTime(new Date());
        if(isEffective==null||isEffective==""){
            user.setStatus("0");
        }else{
            user.setStatus("1");
        }
        //添加用户
        if(StringUtils.isBlank(user.getPswd())){
            user.setPswd(null);
        }else{
            user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
        }
        user.setCreateNameId(String.valueOf(ShiroUtils.getUserId()));
        sysUserMapper.save(user);
        SysUserRole userRole = new SysUserRole();
        userRole.setUid(user.getId());
        userRole.setRid(user.getRoleId());

        //保存用户与角色关系
        sysUserRoleService.insert(userRole);
    }

    @Transactional
    @Override
    public void save(SysUser user) {
        //添加用户
        user.setCreateTime(new Date());
        if(StringUtils.isBlank(user.getPswd())){
            user.setPswd(null);
        }else{
            user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
        }
        user.setCreateNameId(String.valueOf(ShiroUtils.getUserId()));
        sysUserMapper.save(user);
        SysUserRole userRole = new SysUserRole();
        userRole.setUid(user.getId());
        userRole.setRid(user.getRoleId());

        //保存用户与角色关系
        sysUserRoleService.insert(userRole);
    }

    //根据sesisonid获取单个session对象
    private Session getSessionBysessionId(Serializable sessionId){
        Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
        return kickoutSession;
    }

    @Override
    public List<SysUser> getAllUser() {
        return sysUserMapper.getAllUser();
    }

    @Transactional
    @Override
    public void updateUser(SysUser user, String isEffective) {
        if(isEffective == null||isEffective == ""){
            user.setStatus("0");
        }else{
            user.setStatus("1");
        }
        if(StringUtils.isBlank(user.getPswd())){
            user.setPswd(null);
        }else{
            user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
        }
        user.setLastUpdateTime(new Date());
        user.setLastUpdateNameId(String.valueOf(ShiroUtils.getUserId()));
        //更新用户
        sysUserMapper.update(user);

        SysUserRole userRole = new SysUserRole();
        userRole.setUid(user.getId());
        userRole.setRid(user.getRoleId());

        //更新用户与角色关系
        sysUserRoleService.updateUserRole(userRole);
    }

    @Transactional
    @Override
    public void update(SysUser user) {
        //更新用户
        if(StringUtils.isBlank(user.getPswd())){
            user.setPswd(null);
        }else{
            user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
        }
        user.setLastUpdateTime(new Date());
        user.setLastUpdateNameId(String.valueOf(ShiroUtils.getUserId()));
        sysUserMapper.update(user);

        SysUserRole userRole = new SysUserRole();
        userRole.setUid(user.getId());
        userRole.setRid(user.getRoleId());

        //更新用户与角色关系
        sysUserRoleService.updateUserRole(userRole);
    }

    @Transactional
    @Override
    public void deleteBatch(String[] userId) {
        sysUserMapper.deleteBatch(userId);
    }

    @Override
    public int getUser(String username) {
        return sysUserMapper.getUser(username);
    }
}

