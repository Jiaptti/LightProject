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
     * @param userId 用户id
     * @return SysUserRole 对象
     */
    SysUserRole queryRoleIdList(Long userId);


    /**
     * 添加用户角色关系
     * @param map 用户对象map
     */
    void save(Map<String, Object> map);

    /**
     * 更新用户角色关系
     * @param userRole 用户角色对象
     */
    int updateUserRole(SysUserRole userRole);
}