package com.viroyal.light.module.user.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.user.entity.SysPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     *通过各种条件分页查询权限
     * @param params 条件
     * @param page 分页条件
     * @return 权限列表
     */
    List<SysPermission> queryWithCondition(Map<String,Object> params, Pagination page);

    /**
     *通过各种条件查询权限
     * @param params 条件
     * @return 权限列表
     */
    List<SysPermission> queryWithCondition(Map<String,Object> params);

    /**
     * 添加权限
     * @param permission 权限对象
     */
    void save(SysPermission permission);


    /**
     * 更新权限
     * @param permission 权限对象
     */
    void update(SysPermission permission);

    /**
     * 删除权限
     * @return 删除的权限数
     * @param permissionId  权限ID数组
     */
    void deleteBatch(Object[] permissionId);
}