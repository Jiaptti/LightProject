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
    @Transactional
    public void saveOrUpdate(String userId, String roleId) {
        //先删除用户与角色关系
        sysUserRoleMapper.deleteById(userId);

        //保存用户与角色关系
        SysUserRole userRole = new SysUserRole();
        userRole.setUid(userId);
        userRole.setRid(roleId);
        sysUserRoleMapper.insert(userRole);
    }
}
