package com.viroyal.light.module.service.user;

import com.viroyal.light.module.entity.user.SysRole;
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
public interface ISysRoleService extends IService<SysRole> {
    List<SysRole> getRoleListById(Long id);
    String getUserRoleName(Long id);
    void saveRole(SysRole user);
    void updateRole(SysRole user);
}
