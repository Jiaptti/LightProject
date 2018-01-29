package com.viroyal.light.module.user.service;

import com.viroyal.light.module.user.entity.SysPermission;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.user.entity.vo.SysPermissionVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 查询所有权限
     * @return json格式的权限列表
     */
//    String queryAll();

    /**
     *通过各种条件查询权限
     * @param params 条件
     * @return json格式的权限列表
     */
    String queryWithCondition(Map<String,Object> params);


    /**
     * 添加权限
     * @param permission 权限对象
     * @return json格式的结果集
     */
    String savePermission(SysPermissionVo permission);


    /**
     * 更新权限
     * @param permission 权限对象
     * @return json格式的结果集
     */
    String update(SysPermissionVo permission);

    /**
     * 通过id删除权限
     * @param permissionId 权限对象id
     * @return json格式的结果集
     */
    String deleteBatch(String[] permissionId);
}
