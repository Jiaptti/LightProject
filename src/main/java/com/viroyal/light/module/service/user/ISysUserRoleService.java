package com.viroyal.light.module.service.user;

import com.viroyal.light.module.entity.user.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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
