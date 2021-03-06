package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.CustomPage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.user.entity.SysPermission;
import com.viroyal.light.module.user.entity.vo.SysPermissionVo;
import com.viroyal.light.module.user.service.ISysPermissionService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Api("SysPermissionController相关api")
@Controller
@RequestMapping(value = "/permission")
public class SysPermissionController {

    @Autowired
    ISysPermissionService sysPermissionService;

    @ApiOperation("移动端添加权限")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/permissionSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:save")
    public String permissionSave(SysPermissionVo permission){
        return sysPermissionService.savePermission(permission);
    }

    @ApiOperation("移动端删除权限")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/permissionDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:delete")
    public String permissionDelete(@RequestParam(value = "ids[]") String[] ids){
       return sysPermissionService.deleteBatch(ids);
    }

    @ApiOperation("移动端更新权限")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code=400, message = "请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/permissionUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:permission:update")
    public String permissionUpdate(SysPermissionVo permission){
       return sysPermissionService.update(permission);
    }


    @ApiOperation("移动端通过条件查询权限,param填个1就行，移动端发请求，不用带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId",
                    dataType="Int", value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize",
                    dataType="Int", value="多少条"),
            @ApiImplicitParam(paramType="query", name="userId",
                    dataType="Int", value="用户id"),
            @ApiImplicitParam(paramType="query", name="roleId",
                    dataType="Int", value="角色id"),
            @ApiImplicitParam(paramType="query", name="permName",
                    dataType="Int", value="权限名(模糊查询)"),
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
    @RequiresPermissions("sys:permission:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        return sysPermissionService.queryWithCondition(params);
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
