package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysUserLight;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysUserLightService extends IService<SysUserLight> {
    /**
     * 将路灯指派给某个维修员
     * @param userLight 用户路灯关联对象
     */
    void dispatchToUser(SysUserLight userLight);
}
