package com.viroyal.light.module.service.user.impl;

import com.viroyal.light.module.entity.user.SysRole;
import com.viroyal.light.module.dao.user.SysRoleMapper;
import com.viroyal.light.module.service.user.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
