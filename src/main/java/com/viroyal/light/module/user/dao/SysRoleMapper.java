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
    List<SysRole> getRoleListById(Long id);

    String getUserRoleName(Long id);

    void save(SysRole role);

    void update(SysRole role);
}