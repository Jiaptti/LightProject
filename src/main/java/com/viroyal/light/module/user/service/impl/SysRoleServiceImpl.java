package com.viroyal.light.module.user.service.impl;

import com.viroyal.light.module.user.entity.SysRole;
import com.viroyal.light.module.user.dao.SysRoleMapper;
import com.viroyal.light.module.user.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getRoleListById(Long id) {
        return sysRoleMapper.getRoleListById(id);
    }

    @Override
    public String getUserRoleName(Long id) {
        return sysRoleMapper.getUserRoleName(id);
    }

    @Transactional
    @Override
    public void saveRole(SysRole role) {
        sysRoleMapper.save(role);
    }

    @Transactional
    @Override
    public void updateRole(SysRole role) {
        sysRoleMapper.update(role);
    }
}
