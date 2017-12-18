package com.viroyal.light.module.service.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.entity.page.FrontPage;
import com.viroyal.light.module.entity.user.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.entity.user.UserOnlineBo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysUserService extends IService<SysUser> {
    Page<UserOnlineBo> getPagePlus(FrontPage<UserOnlineBo> frontPage);
    void kickout(Serializable sessionId);
    void saveUser(SysUser user, String isEffective);
    List<SysUser> getAllUser();
    void updateUser(SysUser user, String isEffective);
}
