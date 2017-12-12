package com.viroyal.light.module.service.user.impl;

import com.viroyal.light.module.entity.user.SysRole;
import com.viroyal.light.module.entity.user.SysUserRole;
import com.viroyal.light.module.dao.user.SysUserRoleMapper;
import com.viroyal.light.module.service.user.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void updateByUserId(SysUserRole userRole) {
        sysUserRoleMapper.updateByUserId(userRole);
    }
}
