package com.viroyal.light.module.dao.user;

import com.viroyal.light.module.entity.user.SysRole;
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
    public List<SysRole> getRoleListById(String uid);
}