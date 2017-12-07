package com.viroyal.light.module.service.user.impl;

import com.viroyal.light.module.entity.user.SysPermissionInit;
import com.viroyal.light.module.dao.user.SysPermissionInitMapper;
import com.viroyal.light.module.service.user.ISysPermissionInitService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class SysPermissionInitServiceImpl extends ServiceImpl<SysPermissionInitMapper, SysPermissionInit> implements ISysPermissionInitService {

    @Override
    public List<SysPermissionInit> selectAll() {
        return baseMapper.selectAll();
    }
}
