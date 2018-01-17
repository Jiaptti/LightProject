package com.viroyal.light.module.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.module.user.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.user.entity.UserOnlineBo;

import java.io.Serializable;
import java.util.List;
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

    //app存储用户
    void save(SysUser user);

    //获得用户列表
    List<SysUser> getAllUser();

    //后台更新用户
    void updateUser(SysUser user, String isEffective);

    //app更新
    void update(SysUser user);

    //批量删除用户
    void deleteBatch(String[] userId);

    //查询账号是否存在
    int getUser(String username);


    /**
     *通过各种条件分页查询用户
     * @param params 条件
     * @return 用户列表
     */
    DatePage<SysUser> queryWithCondition(Map<String,Object> params);
}
