package com.viroyal.light.module.user.service;

import com.viroyal.light.module.user.entity.SysRolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    /**
     * 添加角色权限
     * @param  rolePermissionList 角色权限对象
     */
    void save(List<SysRolePermission> rolePermissionList);

    /**
     * 更新角色权限关联
     * @param  rolePermission 角色权限对象
     */
    void update(SysRolePermission rolePermission);

    /**
     * 删除角色权限关联
     * @param  ids 角色权限id数组
     */
    void deleteBatch(Object[] ids);
}
