package com.viroyal.light.module.controller.user;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.entity.page.CustomPage;
import com.viroyal.light.module.entity.page.FrontPage;
import com.viroyal.light.module.entity.user.SysUser;
import com.viroyal.light.module.entity.user.SysUserRole;
import com.viroyal.light.module.entity.user.UserOnlineBo;
import com.viroyal.light.module.service.user.ISysUserRoleService;
import com.viroyal.light.module.service.user.ISysUserService;
import com.viroyal.light.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Controller
@RequestMapping(value = "/user")
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    ISysUserRoleService sysUserRoleService;


    // 跳转到用户管理页面
    @RequestMapping(value = "/userPage")
    public String userPage(String edit, Model modle) {
        // edit判断是否编辑成功
        modle.addAttribute("edit", edit);
        return "user/user";
    }

    // 跳轉到編輯頁面edit
    @RequestMapping(value = "/editPage/{Id}")
    public String editPage(@PathVariable("Id") String Id, Model model) {
        if (Id.equals("add")) {
        } else {
            SysUser user = sysUserService.selectById(Id);
            SysUserRole userRole = sysUserRoleService.selectById(Long.valueOf(Id));
            System.out.print("roleId = " + userRole.getRid());
            user.setRoleId(userRole.getRid());
            model.addAttribute("user", user);
        }
        return "user/edit";
    }

    // 增加和修改
    @RequestMapping(value = "/edit")
    public String edit(SysUser user, String isEffective, Model model) {
        if (sysUserService.saveUser(user, isEffective)) {
            return "forward:userPage?edit=true";
        } else {
            return "forward:userPage?edit=false";
        }
    }

    // 用户列表分页json
    @RequestMapping(value = "/getUserListWithPager")
    @ResponseBody
    public String getUserListWithPager(FrontPage<SysUser> page) {
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        String keyWords = page.getKeywords();
        if (keyWords != null && keyWords != "")
            wrapper.like("nickname", keyWords);
        Page<SysUser> pageList = sysUserService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysUser> customPage = new CustomPage<SysUser>(pageList);
        return JSON.toJSONString(customPage);
    }

    // 刪除用户
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            sysUserService.deleteBatchIds(Arrays.asList(ids));
            resultMap.put("flag", true);
            resultMap.put("msg", "刪除成功！");
        } catch (Exception e) {
            resultMap.put("flag", false);
            resultMap.put("msg", e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    // 跳转到在线用户管理页面
    @RequestMapping(value = "/onlineUserPage")
    public String onlineUserPage() {
        return "user/onlineUser";
    }

    // 在线用户列表json
    @RequestMapping(value = "/onlineUsers")
    @ResponseBody
    public String OnlineUsers(FrontPage<UserOnlineBo> frontPage) {
        Page<UserOnlineBo> pageList = sysUserService.getPagePlus(frontPage);
        CustomPage<UserOnlineBo> customPage = new CustomPage<UserOnlineBo>(pageList);
        return JSON.toJSONString(customPage);
    }

    // 强制踢出用户
    @RequestMapping(value = "/kickout")
    @ResponseBody
    public String kickout(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            for (String sessionId : ids) {
                sysUserService.kickout(sessionId);
            }
            resultMap.put("flag", true);
            resultMap.put("msg", "强制踢出成功！");
        } catch (Exception e) {
            resultMap.put("flag", false);
            resultMap.put("msg", e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
