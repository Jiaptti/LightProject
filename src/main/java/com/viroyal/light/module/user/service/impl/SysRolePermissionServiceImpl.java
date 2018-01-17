package com.viroyal.light.module.user.service.impl;

import com.viroyal.light.module.user.entity.SysRolePermission;
import com.viroyal.light.module.user.dao.SysRolePermissionMapper;
import com.viroyal.light.module.user.service.ISysRolePermissionService;
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
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Transactional
    @Override
    public void save(List<SysRolePermission> rolePermissionList) {
        sysRolePermissionMapper.save(rolePermissionList);
    }

    @Transactional
    @Override
    public void update(SysRolePermission rolePermission) {
        sysRolePermissionMapper.update(rolePermission);
    }

    @Transactional
    @Override
    public void deleteBatch(Object[] ids) {
        sysRolePermissionMapper.deleteBatch(ids);
    }
}
