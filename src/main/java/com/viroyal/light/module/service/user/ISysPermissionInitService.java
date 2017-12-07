package com.viroyal.light.module.service.user;

import com.viroyal.light.module.entity.user.SysPermissionInit;
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
public interface ISysPermissionInitService extends IService<SysPermissionInit> {
    public List<SysPermissionInit> selectAll();
}
