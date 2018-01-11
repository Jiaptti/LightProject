package com.viroyal.light.module.user.service;

import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.user.entity.SysPermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
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
     *通过各种条件查询权限
     * @param params 条件
     * @return 权限列表
     */
    DatePage<SysPermission> queryWithCondition(Map<String,Object> params);
}
