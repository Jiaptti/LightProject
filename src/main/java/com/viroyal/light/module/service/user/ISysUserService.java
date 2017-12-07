package com.viroyal.light.module.service.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.entity.page.FrontPage;
import com.viroyal.light.module.entity.user.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.entity.user.UserOnlineBo;

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
