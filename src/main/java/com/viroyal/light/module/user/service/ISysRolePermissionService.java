package com.viroyal.light.module.user.service;

import com.viroyal.light.module.user.entity.SysRolePermission;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.user.entity.vo.SysRolePermissionVo;

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
     * @param  roleId 角色
     * @param  permissionId 权限id(多的用逗号隔开)
     * @return json格式的结果
     */
    String save(String roleId, String permissionId);

    /**
     * 更新角色权限关联
     * @param  rolePermission 角色权限对象
     * @return json格式的结果
     */
    String update(SysRolePermissionVo rolePermission);

    /**
     * 删除角色权限关联
     * @param  ids 角色权限id数组
     * @return json格式的结果
     */
    String deleteBatch(Object[] ids);
}
