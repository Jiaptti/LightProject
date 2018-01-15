package com.viroyal.light.module.user.dao;

import com.viroyal.light.module.user.entity.SysRole;
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
}