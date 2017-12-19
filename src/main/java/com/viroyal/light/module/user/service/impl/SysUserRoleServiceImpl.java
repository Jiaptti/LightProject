package com.viroyal.light.module.user.service.impl;

import com.viroyal.light.module.user.entity.SysUserRole;
import com.viroyal.light.module.user.dao.SysUserRoleMapper;
import com.viroyal.light.module.user.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole getUserRole(Long id) {
        return sysUserRoleMapper.queryRoleIdList(id);
    }

    @Override
    @Transactional
    public void updateUserRole(SysUserRole userRole) {
        sysUserRoleMapper.updateUserRole(userRole);
    }
}
