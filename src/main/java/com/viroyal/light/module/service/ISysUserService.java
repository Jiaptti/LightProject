package com.viroyal.light.module.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.entity.FrontPage;
import com.viroyal.light.module.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.entity.UserOnlineBo;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysUserService extends IService<SysUser> {
    public Page<UserOnlineBo> getPagePlus(FrontPage<UserOnlineBo> frontPage);
    public void kickout(Serializable sessionId);
}
