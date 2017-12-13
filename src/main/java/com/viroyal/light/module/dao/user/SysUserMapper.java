package com.viroyal.light.module.dao.user;

import com.viroyal.light.module.entity.user.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
    void save(SysUser user);
}