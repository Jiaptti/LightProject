package com.viroyal.light.module.user.dao;

import com.viroyal.light.module.user.entity.SysUser;
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
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 添加用户
     */
    void save(SysUser user);

    /**
     * 查询所有用户
     */
    List<SysUser> getAllUser();

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    void update(SysUser user);
}