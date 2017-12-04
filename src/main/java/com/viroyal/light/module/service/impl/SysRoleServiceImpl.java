package com.viroyal.light.module.service.impl;

import com.viroyal.light.module.entity.SysRole;
import com.viroyal.light.module.dao.SysRoleMapper;
import com.viroyal.light.module.service.ISysRoleService;
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
    public List<SysRole> getRoleListById(String uid) {
        return sysRoleMapper.getRoleListById(uid);
    }
}
