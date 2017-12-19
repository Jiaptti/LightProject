package com.viroyal.light.module.user.dao;

import com.viroyal.light.module.user.entity.SysUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    SysUserRole queryRoleIdList(Long userId);
    void save(Map<String, Object> map);
    void updateUserRole(SysUserRole userRole);
}