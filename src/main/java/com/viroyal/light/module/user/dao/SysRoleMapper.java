package com.viroyal.light.module.user.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.user.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 添加角色
     * @param role 角色对象
     */
    void save(SysRole role);

    /**
     * 更新角色
     * @param role 角色对象
     */
    void update(SysRole role);

    /**
     * 删除角色
     * @param ids 角色对象id数组
     */
    void deleteBatch(Object[] ids);

    /**
     * 分页查询角色
     * @param params 查询条件
     * @param page 分页条件
     * @return 角色集合
     */
    List<SysRole> queryWithCondition(Map<String, Object> params, Pagination page);

    /**
     * 分页查询角色
     * @param params 查询条件
     * @return 角色集合
     */
    List<SysRole> queryWithCondition(Map<String, Object> params);
}