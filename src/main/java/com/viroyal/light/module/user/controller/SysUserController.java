package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.MyDES;
import com.viroyal.light.common.page.CustomPage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.module.user.entity.SysUser;
import com.viroyal.light.module.user.entity.SysUserRole;
import com.viroyal.light.module.user.entity.UserOnlineBo;
import com.viroyal.light.module.user.service.ISysUserRoleService;
import com.viroyal.light.module.user.service.ISysUserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Api("用户Controller相关api")
@Controller
@RequestMapping(value = "/user")
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    ISysUserRoleService sysUserRoleService;


    // 跳转到用户管理页面
    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage(String edit, Model model) {
        // edit判断是否编辑成功
        model.addAttribute("edit", edit);
        return "user/user";
    }

    //跳转到用户添加页面
    @RequestMapping(value = "/forward/save", method = RequestMethod.GET)
    public String save() {
        return "user/edit";
    }


    @ApiOperation("移动端通过id获得用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "No Result")
    })
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:user:info")
    public String getUser(@RequestParam("id") String id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysUser user = sysUserService.selectById(id);
        SysUserRole userRole = sysUserRoleService.getUserRole(Long.valueOf(id));
        user.setRoleId(userRole.getRid());
        if (user == null && user.getExist() == 1) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.USER, user);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.USER, user);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    // 跳轉到編輯頁面edit
    @RequestMapping(value = "/editPage/{Id}", method = RequestMethod.GET)
    public String editPage(@PathVariable("Id") String Id, Model model) {
        SysUser user = sysUserService.selectById(Id);
        SysUserRole userRole = sysUserRoleService.getUserRole(Long.valueOf(Id));
        user.setRoleId(userRole.getRid());
        String pswd = MyDES.decryptBasedDes(user.getPswd());
        pswd = pswd.substring(0, pswd.indexOf(user.getUsername()));
        user.setPswd(pswd);
        model.addAttribute(BaseConstant.USER, user);
        return "user/edit";
    }

    //添加用户
    @ApiOperation("移动端添加用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "更新成功"),
            @ApiResponse(code = 500, message = "更新失败")
    })
    @RequestMapping(value = "/userSave", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:save")
    @ResponseBody
    public String saveUser(@Valid @RequestBody SysUser user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysUserService.save(user);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
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

    @ApiOperation("移动端更新用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "更新成功"),
            @ApiResponse(code = 500, message = "更新失败")
    })
    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:update")
    @ResponseBody
    public String update(SysUser user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (user.getId() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_USER_ID);
        } else if (user.getRoleId() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_USER_ROLE_ERROR);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysUserService.update(user);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }


    @RequestMapping(value = "/getUserListWithPager", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public String getUserListWithPager(FrontPage<SysUser> page) {
        page.setPageSize(10);
        Wrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        String keyWords = page.getKeywords();
        if (!StringUtils.isEmpty(keyWords))
            wrapper.like("username", keyWords);

        wrapper.eq("flag", 1);
        Page<SysUser> pageList = sysUserService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysUser> customPage = new CustomPage<SysUser>(pageList);
        return JSON.toJSONString(customPage);
    }

    @ApiOperation("移动端分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "页面条数"),
            @ApiImplicitParam(paramType = "query", name = "userId",
                    dataType = "Int", value = "用户Id"),
            @ApiImplicitParam(paramType = "query", name = "nickname",
                    dataType = "String", value = "用户姓名(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "username",
                    dataType = "String", value = "用户账号(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "sort",
                    dataType = "String", value = "排序方式asc/desc(可以后面什么都不带默认升序)")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/getUserPage", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public String userPage(@RequestParam Map<String, Object> params) {
        if ((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }
        DatePage<SysUser> datePage = sysUserService.queryWithCondition(params);
        return JSON.toJSONString(datePage);
    }

    @ApiOperation("移动端查询用户列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public String getUserList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysUser> userList = sysUserService.getAllUser();
        if (userList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, userList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端删除用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @RequestMapping(value = "/userDelete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:user:delete")
    public String userBatchUpdate(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysUserService.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    // 跳转到在线用户管理页面
    @RequestMapping(value = "/onlineUserPage", method = RequestMethod.GET)
    public String onlineUserPage() {
        return "/user/onlineUser";
    }

    // 在线用户列表json
    @RequestMapping(value = "/onlineUsers", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:user:list")
    public String OnlineUsers(FrontPage<UserOnlineBo> frontPage) {
        Page<UserOnlineBo> pageList = sysUserService.getPagePlus(frontPage);
        CustomPage<UserOnlineBo> customPage = new CustomPage<UserOnlineBo>(pageList);
        return JSON.toJSONString(customPage);
    }

    // 强制踢出用户
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:user:kickout")
    public String kickout(@RequestParam(value = "ids[]") String[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            for (String sessionId : ids) {
                sysUserService.kickout(sessionId);
            }
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.KICKOUT_SUCCESS);
        } catch (Exception e) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.KICKOUT_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
