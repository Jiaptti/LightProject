package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.vo.SysBasicLightPoleVo;
import com.viroyal.light.module.light.service.ISysBasicLightPoleService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 灯杆基础信息表 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Api("SysBasicLightPoleController相关api")
@Controller
@RequestMapping(value = "/pole")
public class SysBasicLightPoleController {
	@Autowired
    ISysBasicLightPoleService sysBasicLightPoleService;

    @ApiOperation("移动端添加路灯灯杆")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/poleSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:pole:save")
    public String poleSave(SysBasicLightPoleVo lightPoleVo){
        return sysBasicLightPoleService.save(lightPoleVo);
    }

    @ApiOperation("移动端更新路灯灯杆")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/poleUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:box:update")
    public String poleUpdate(SysBasicLightPoleVo lightPoleVo){
        return sysBasicLightPoleService.update(lightPoleVo);
    }

    @ApiOperation("移动端删除路灯灯杆")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/poleDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:pole:delete")
    public String poleDelete(@RequestParam(value = "ids[]") String[] ids){
        return sysBasicLightPoleService.deleteBatch(ids);
    }


    @ApiOperation("通过条件查询路灯灯杆结果集")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "poleBrand",
                    dataType = "String", value = "灯杆品牌(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "poleType",
                    dataType = "String", value = "灯杆型号(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "sort",
                    dataType = "String", value = "排序方式(asc/desc)")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:pole:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params) {
        return sysBasicLightPoleService.queryWithCondition(params);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String processUnauthenticatedException(Exception ex) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ex.printStackTrace();
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
