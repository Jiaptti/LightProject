package com.viroyal.light.module.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.module.user.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.user.entity.UserOnlineBo;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysUserService extends IService<SysUser> {
    //分页获得用户列表
    Page<UserOnlineBo> getPagePlus(FrontPage<UserOnlineBo> frontPage);

    //logout 踢出异地登录
    void kickout(Serializable sessionId);

    //logout 用户退出
    void logout(Serializable sessionId);

    //后台存储用户
    void saveUser(SysUser user, String isEffective);

    /**
     * 添加用户
     * @param user 用户对象
     * @return json格式的结果
     */
    String save(SysUser user);

    /**
     * 查询所有用户
     * @return json格式的用户列表
     */
    String getAllUser();

    //后台更新用户
    void updateUser(SysUser user, String isEffective);

    /**
     * 更新用户
     * @param user 用户对象
     * @return json格式的结果
     */
    String update(SysUser user);

    /**
     * 批量删除用户
     * @param userId 用户id数组
     * @return json格式的结果
     */
    String deleteBatch(String[] userId);

    /**
     *通过各种条件分页查询用户
     * @param params 条件
     * @return json格式的用户列表
     */
    String queryWithCondition(Map<String,Object> params);

    /**
     * 通过id查询对应用户
     * @param userId 用户id
     * @return json格式的用户对象
     */
    String getUserById(Long userId);


    /**
     * 更新用户密码
     * @param params 用户密码和id等参数
     * @return json格式的用户对象
     */
    String updateUserPassword(Map<String,Object> params);
}
