package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.viroyal.light.common.utils.MyDES;
import com.viroyal.light.module.user.entity.page.CustomPage;
import com.viroyal.light.module.user.entity.page.FrontPage;
import com.viroyal.light.module.user.entity.SysUser;
import com.viroyal.light.module.user.entity.SysUserRole;
import com.viroyal.light.module.user.entity.UserOnlineBo;
import com.viroyal.light.module.user.service.ISysUserRoleService;
import com.viroyal.light.module.user.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 前端控制器
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
    public String userPage(String edit, Model model) {
        // edit判断是否编辑成功
        model.addAttribute("edit", edit);
        return "user/user";
    }

    //跳转到用户添加页面
    @RequestMapping(value = "/forward/save")
    public String save() {
        return "user/edit";
    }


    @RequestMapping(value = "/getUser")
    @ResponseBody
    @RequiresPermissions("sys:user:info")
    public String getUser(@RequestParam("id") String id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysUser user = sysUserService.selectById(id);
        if(user == null) {
            resultMap.put("code", 500);
            resultMap.put("user", user);
            resultMap.put("message","no result");
        } else {
            resultMap.put("code", 200);
            resultMap.put("user", user);
            resultMap.put("message","success");
        }
        return JSON.toJSONString(resultMap);
    }

    // 跳轉到編輯頁面edit
    @RequestMapping(value = "/editPage/{Id}")
    public String editPage(@PathVariable("Id") String Id, Model model) {
        SysUser user = sysUserService.selectById(Id);
        SysUserRole userRole = sysUserRoleService.getUserRole(Long.valueOf(Id));
        user.setRoleId(userRole.getRid());
        user.setPswd(MyDES.decryptBasedDes(user.getPswd()));
        model.addAttribute("user", user);
        return "user/edit";
    }

    //添加用户
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:save")
    public String saveUser(SysUser user, String isEffective) {
        sysUserService.saveUser(user, isEffective);
        return "forward:userPage?edit=true";
    }

    //移动端添加用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:save")
    @ResponseBody
    public String save(SysUser user){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put("code", 200);
            sysUserService.save(user);
            resultMap.put("message", "save success!");
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put("code", 500);
            resultMap.put("message", "error = " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    // 更新用户
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:update")
    public String updateUser(SysUser user, String isEffective) {
        sysUserService.updateUser(user, isEffective);
        return "forward:userPage?edit=true";
    }

    //移动端添加用户
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:update")
    @ResponseBody
    public String update(SysUser user){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put("code", 200);
            sysUserService.update(user);
            resultMap.put("message", "update success!");
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put("code", 500);
            resultMap.put("message", "failure error = " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    // 用户列表分页json
    @RequestMapping(value = "/getUserListWithPager")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public String getUserListWithPager(FrontPage<SysUser> page) {
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        String keyWords = page.getKeywords();
        if (keyWords != null && keyWords != "")
            wrapper.like("username", keyWords);
        Page<SysUser> pageList = sysUserService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysUser> customPage = new CustomPage<SysUser>(pageList);
        return JSON.toJSONString(customPage);
    }

    //移动端查询用户列表
    @RequestMapping(value = "/list")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public String getUserList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysUser> userList = sysUserService.selectByMap(new HashMap<>());
        if (userList.size() > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "成功");
        } else {
            resultMap.put("code", 500);
            resultMap.put("message", "失败");
        }
        resultMap.put("list", userList);
        return JSON.toJSONString(resultMap);
    }

    // 刪除用户
    @RequestMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("sys:user:delete")
    @Transactional
    public String delete(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(sysUserService.deleteBatch(ids) > 0){
            resultMap.put("status", "200");
            resultMap.put("message", "delete success！");
        } else {
            resultMap.put("code", "500");
            resultMap.put("message", "delete failure");
        }
        return JSON.toJSONString(resultMap);
    }

    // 跳转到在线用户管理页面
    @RequestMapping(value = "/onlineUserPage")
    public String onlineUserPage() {
        return "/user/onlineUser";
    }

    // 在线用户列表json
    @RequestMapping(value = "/onlineUsers")
    @ResponseBody
    @RequiresPermissions("sys:user:list")
    public String OnlineUsers(FrontPage<UserOnlineBo> frontPage) {
        Page<UserOnlineBo> pageList = sysUserService.getPagePlus(frontPage);
        CustomPage<UserOnlineBo> customPage = new CustomPage<UserOnlineBo>(pageList);
        return JSON.toJSONString(customPage);
    }

    // 强制踢出用户
    @RequestMapping(value = "/kickout")
    @ResponseBody
    @RequiresPermissions("sys:user:kickout")
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
