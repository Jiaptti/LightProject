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
     * @param user 用户对象
     */
    void save(SysUser user);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<SysUser> getAllUser();


    /**
     * 删除用户
     * @return 删除的用户数
     * @param userId  用户ID数组
     */
    void deleteBatch(Object[] userId);

    /**
     * 查询用户的所有权限
     * @return 权限列表
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询账号是否存在
     * @return 用户条数
     * @param username  账号
     */
    int getUser(String username);

    /**
     * 更新用户
     * @param user  用户
     */
    void update(SysUser user);
}