package com.viroyal.light.module.user.service;

import com.viroyal.light.module.user.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    SysUserRole getUserRole(Long id);
    void updateUserRole(SysUserRole userRole);
}
