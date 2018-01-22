package com.viroyal.light.module.user.dao;

import com.viroyal.light.module.user.entity.SysRolePermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */

@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    /**
     * 添加角色权限关联
     * @param  rolePermissionList 角色权限对象
     */
    void save(List<SysRolePermission> rolePermissionList);

    /**
     * 更新角色权限关联
     * @param  rolePermission 角色权限对象
     * @return 更新记录条数
     */
    int update(SysRolePermission rolePermission);

    /**
     * 删除角色权限关联
     * @param  ids 角色权限id数组
     */
    void deleteBatch(Object[] ids);

}