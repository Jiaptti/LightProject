package com.viroyal.light.module.user.service;

import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.user.entity.SysPermission;
import com.baomidou.mybatisplus.service.IService;

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
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 查询所有权限
     * @return 权限列表
     */
    List<SysPermission> queryAll();

    /**
     *通过各种条件查询权限
     * @param params 条件
     * @return 权限列表
     */
    DatePage<SysPermission> queryWithCondition(Map<String,Object> params);


    /**
     * 添加权限
     * @param permission 权限对象
     */
    void savePermission(SysPermission permission);


    /**
     * 更新权限
     * @param permission 权限对象
     */
    void update(SysPermission permission);

    /**
     * 通过id删除权限
     * @param permissionId 权限对象id
     */
    void deleteBatch(String[] permissionId);
}
