package com.viroyal.light.module.user.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.user.entity.SysUser;
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
    int deleteBatch(Object[] userId);

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
    int update(SysUser user);

    /**
     *通过各种条件分页查询用户
     * @param params 条件
     * @param page 分页条件
     * @return 用户列表
     */
    List<SysUser> queryWithCondition(Map<String,Object> params, Pagination page);

    /**
     *通过各种条件查询用户
     * @param params 条件
     * @return 用户列表
     */
    List<SysUser> queryWithCondition(Map<String,Object> params);

    /**
     * 通过id查询对应用户
     * @param userId 用户id
     * @return 用户对象
     */
    SysUser getUserById(Long userId);
}