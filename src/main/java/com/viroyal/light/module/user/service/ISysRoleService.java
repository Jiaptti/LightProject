package com.viroyal.light.module.user.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.user.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  角色服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysRoleService extends IService<SysRole> {

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
    DatePage<SysRole> queryWithCondition(Map<String, Object> params);
}
