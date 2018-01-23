package com.viroyal.light.module.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.*;
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
 * 服务实现类
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
            if (session.getAttribute("kickout") == null ? false : true) continue;
            UserOnlineBo onlineUser = getSessionBo(session);
            if (onlineUser != null) {
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
    private UserOnlineBo getSessionBo(Session session) {
        //获取session登录信息。
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (null == obj) {
            return null;
        }
        //确保是 SimplePrincipalCollection对象。
        if (obj instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            /**
             * 获取用户登录的，@link SampleRealm.doGetAuthenticationInfo(...)方法中
             * return new SimpleAuthenticationInfo(user,user.getPswd(), getName());的user 对象。
             */
            obj = spc.getPrimaryPrincipal();
            if (null != obj && obj instanceof SysUser) {
                //存储session + user 综合信息
                UserOnlineBo userBo = new UserOnlineBo((SysUser) obj);
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
    public void kickout(Serializable sessionId) {
        SysUser user = sysUserMapper.selectById(sessionId);
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
        if (isEffective == null || isEffective == "") {
            user.setStatus("0");
        } else {
            user.setStatus("1");
        }
        //添加用户
        if (StringUtils.isBlank(user.getPswd())) {
            user.setPswd(null);
        } else {
            user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
        }
        user.setCreateNameId(String.valueOf(ShiroUtils.getUserId()));
        sysUserMapper.save(user);
        SysUserRole userRole = new SysUserRole();
        userRole.setUid(user.getId());
        userRole.setRid(user.getRoleId());
        userRole.setExist(1);

        //保存用户与角色关系
        sysUserRoleService.insert(userRole);
    }

    @Transactional
    @Override
    public String save(SysUser user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(user.getNickname())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_NAME_IS_EMPTY);
            return JSON.toJSONString(resultMap);
        } else if (!CommonUtil.rightLength(user.getNickname(), 2, 5)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.USER_NAME_LENGTH, 2, 5));
            return JSON.toJSONString(resultMap);
        } else if (!CommonUtil.isRightNameFormat(user.getNickname())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_NAME_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if (StringUtils.isBlank(user.getUsername())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_ACCOUNT_IS_EMPTY);
            return JSON.toJSONString(resultMap);
        } else if (!CommonUtil.rightLength(user.getUsername().trim(), 5, 15)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.USER_ACCOUNT_LENGTH, 5, 15));
            return JSON.toJSONString(resultMap);
        } else if (sysUserMapper.getUser(user.getUsername()) != 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_EXIST);
            return JSON.toJSONString(resultMap);
        } else if (StringUtils.isBlank(user.getPswd())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_PASSWORD_IS_EMPTY);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(user.getPswd(), 6, 15)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.USER_PASSWORD_LENGTH, 6, 15));
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getPhone()) && !CommonUtil.isPhone(user.getPhone().trim())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_PHONE_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getEmail()) && !CommonUtil.isEmail(user.getEmail().trim())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_EMAIL_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(user.getStatus())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_NO_STATUS);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(user.getStatus().trim(), 0, 2)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.USER_STATUS_LENGTH, 0, 2));
            return JSON.toJSONString(resultMap);
        } else if (user.getRoleId() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.USER_ROLE_ID_IS_EMPTY);
            return JSON.toJSONString(resultMap);
        } else {
            //添加用户
            user.setCreateTime(new Date());
            if (StringUtils.isBlank(user.getPswd())) {
                user.setPswd(null);
            } else {
                user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
            }
            user.setCreateNameId(String.valueOf(ShiroUtils.getUserId()));
            sysUserMapper.save(user);
            SysUserRole userRole = new SysUserRole();
            userRole.setUid(user.getId());
            userRole.setRid(user.getRoleId());
            userRole.setExist(1);

            //保存用户与角色关系
            sysUserRoleService.insert(userRole);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    //根据sesisonid获取单个session对象
    private Session getSessionBysessionId(Serializable sessionId) {
        Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
        return kickoutSession;
    }

    //获得用户列表
    @Override
    public String getAllUser() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysUser> userList = sysUserMapper.getAllUser();
        if (userList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, userList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public void updateUser(SysUser user, String isEffective) {
        if (isEffective == null || isEffective == "") {
            user.setStatus("0");
        } else {
            user.setStatus("1");
        }
        if (StringUtils.isBlank(user.getPswd())) {
            user.setPswd(null);
        } else {
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
    public String update(SysUser user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (user.getId() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getNickname()) && !CommonUtil.rightLength(user.getNickname(), 2, 5)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.USER_NAME_LENGTH, 2, 5));
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getNickname()) && !CommonUtil.isRightNameFormat(user.getNickname())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.USER_NAME_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getUsername()) && !CommonUtil.rightLength(user.getUsername().trim(), 5, 15)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.USER_ACCOUNT_LENGTH, 5, 15));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(user.getPswd()) && !CommonUtil.rightLength(user.getPswd(), 6, 15)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.USER_PASSWORD_LENGTH, 6, 15));
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getPhone()) && !CommonUtil.isPhone(user.getPhone().trim())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.USER_PHONE_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if (!StringUtils.isBlank(user.getEmail()) && !CommonUtil.isEmail(user.getEmail().trim())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.USER_EMAIL_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(user.getStatus()) && !CommonUtil.rightLength(user.getStatus(),0,2)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.USER_STATUS_LENGTH, 0, 2));
            return JSON.toJSONString(resultMap);
        } else {
            //更新用户
            if (StringUtils.isBlank(user.getPswd())) {
                user.setPswd(null);
            } else {
                user.setPswd(MyDES.encryptBasedDes(user.getPswd() + user.getUsername()));
            }
            user.setLastUpdateTime(new Date());
            user.setLastUpdateNameId(String.valueOf(ShiroUtils.getUserId()));
            sysUserMapper.update(user);

            SysUserRole userRole = new SysUserRole();
            if(user.getRoleId() != null){
                userRole.setUid(user.getId());
                userRole.setRid(user.getRoleId());
                //更新用户与角色关系
                sysUserRoleService.updateUserRole(userRole);
            }
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String deleteBatch(String[] userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(userId.length == 0){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + BaseConstant.NO_DELETE_ID);
            return JSON.toJSONString(resultMap);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysUserMapper.deleteBatch(userId);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
            }
            return JSON.toJSONString(resultMap);
        }
    }


    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DatePage<SysUser> datePage = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Page<SysUser> page = new Page<SysUser>();
        int pageId = 0, pageSize = 0;
        if ((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }else if(params.containsKey("userId") && !NumberUtils.isNumber(params.get("userId").toString())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.ID_ERROR);
            return JSON.toJSONString(resultMap);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getKey().toString().equals("pageId")) {
                pageId = Integer.parseInt(entry.getValue().toString());
            } else if (entry.getKey().toString().equals("pageSize")) {
                pageSize = Integer.parseInt(entry.getValue().toString());
            } else {
                if (NumberUtils.isNumber(entry.getValue().toString())) {
                    params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                    if (entry.getKey().toString().equals("cityId")) {
                        params.put(entry.getKey(), Long.valueOf(entry.getValue().toString().substring(0, 3)));
                    }
                }
            }
        }
        if (pageId == 0 || pageSize == 0) {
            List<SysUser> data = sysUserMapper.queryWithCondition(params);
            page.setSize(data.size());
            page.setTotal(data.size());
            page.setRecords(data);
        } else {
            page.setCurrent(pageId);
            page.setSize(pageSize);
            page.setRecords(sysUserMapper.queryWithCondition(params, page));
        }
        datePage = new DatePage<SysUser>(page);
        datePage.setCode(BaseConstant.SUCCESS_CODE);
        datePage.setMessage(BaseConstant.SUCCESS_RESULT);
        return JSON.toJSONString(datePage);
    }

    @Override
    public String getUserById(Long userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysUser user = sysUserMapper.getUserById(userId);
        if (user == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.USER, user);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String updateUserPassword(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(!params.containsKey("userId")){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " +  BaseConstant.NO_USER_ID);
            return JSON.toJSONString(resultMap);
        }else if(!params.containsKey("password")){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " +  BaseConstant.NO_ORIGIN_PASSWORD);
            return JSON.toJSONString(resultMap);
        } else if(!params.containsKey("newPassword")){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " +  BaseConstant.NO_NEW_PASSWORD);
            return JSON.toJSONString(resultMap);
        }  else {
            SysUser myUser = sysUserMapper.getUserPswd(Long.parseLong(params.get("userId").toString()));
            if(myUser == null){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " +  BaseConstant.INPUT_RIGHT_USER_ID);
            } else {
                String password = MyDES.decryptBasedDes(myUser.getPswd());
                String pswd = params.get("password").toString() + myUser.getUsername();
                if(StringUtils.equals(password, pswd)){
                    SysUser user = new SysUser();
                    user.setId(Long.parseLong(params.get("userId").toString()));
                    user.setPswd(MyDES.encryptBasedDes(params.get("newPassword").toString() + myUser.getUsername()));
                    try{
                        sysUserMapper.update(user);
                        resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                        resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
                    } catch (Exception e){
                        e.printStackTrace();
                        resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                        resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
                    }
                } else {
                    resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                    resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " +  BaseConstant.CHECKOUT_USER_PASSWORD);
                }
            }
        }
        return JSON.toJSONString(resultMap);
    }
}

