package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.CustomPage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.user.entity.SysRole;
import com.viroyal.light.module.user.entity.SysRolePermission;
import com.viroyal.light.module.user.entity.vo.SysRolePermissionVo;
import com.viroyal.light.module.user.entity.vo.SysRoleVo;
import com.viroyal.light.module.user.service.ISysRolePermissionService;
import com.viroyal.light.module.user.service.ISysRoleService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
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
@Api("SysRoleController相关api")
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    ISysRolePermissionService sysRolePermissionService;

    @ApiOperation("移动端添加角色")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:save")
    public String save(SysRoleVo role){
        return sysRoleService.save(role);
    }

    @ApiOperation("移动端给角色添加权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="roleId",
                    dataType="Int", value="角色id"),
            @ApiImplicitParam(paramType="query", name="permissionId",
                    dataType="String", value="权限id(多个用逗号隔开)"),
    })
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/dispatchPermission", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:save")
    public String dispatchPermission(@RequestParam(value = "roleId") String roleId, @RequestParam(value = "permissionId") String permissionId){
        return sysRolePermissionService.save(roleId, permissionId);
    }

    @ApiOperation("移动端更新角色")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:update")
    public String update(SysRoleVo role){
        return sysRoleService.update(role);
    }

    @ApiOperation("移动端更新角色权限关联")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/updateRolePermission", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:update")
    public String updateRolePermission(SysRolePermissionVo rolePermission){
       return sysRolePermissionService.update(rolePermission);
    }


    @ApiOperation("移动端删除角色")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public String delete(@RequestParam(value = "ids[]") String[] ids){
        return sysRoleService.deleteBatch(ids);
    }

    @ApiOperation("移动端删除角色权限关联")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/deleteRolePermission", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public String deleteRolePermission(@RequestParam(value = "ids[]") String[] ids){
       return sysRolePermissionService.deleteBatch(ids);
    }


    @ApiOperation("移动端通过条件查询角色,param填个1就行，移动端发请求，不用带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId",
                    dataType="Int", value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize",
                    dataType="Int", value="多少条"),
            @ApiImplicitParam(paramType="query", name="userId",
                    dataType="Int", value="用户id"),
            @ApiImplicitParam(paramType="query", name="userName",
                    dataType="String", value="用户姓名"),
            @ApiImplicitParam(paramType="query", name="userAccount",
                    dataType="String", value="用户账号"),
            @ApiImplicitParam(paramType="query", name="roleName",
                    dataType="String", value="角色名(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="roleType",
                    dataType="String", value="角色类型(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="sort",
                    dataType="String", value="排序方式"),
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        return sysRoleService.queryWithCondition(params);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String processUnauthenticatedException(Exception ex) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(ex instanceof UnauthorizedException){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_AUTORITY);
        } else if(ex instanceof BindException){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_EXCEPTION + " : " + BaseConstant.EXCEPTION_FORMAT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_EXCEPTION + " : " +
                    BaseConstant.EXCEPTION_TYPE + " = " +ex.getClass().getSimpleName() + ", " +
                    BaseConstant.EXCEPTION_MESSAGE + " = " + ex.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
