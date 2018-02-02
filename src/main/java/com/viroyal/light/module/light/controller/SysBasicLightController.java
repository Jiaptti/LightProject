package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.vo.SysBasicLightVo;
import com.viroyal.light.module.light.service.ISysBasicLightService;
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
 * 路灯基础信息表 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Api("SysBasicLightController相关api")
@Controller
@RequestMapping(value = "/basic")
public class SysBasicLightController {
    @Autowired
    ISysBasicLightService sysBasicLightService;

    @ApiOperation("移动端添加安装路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/basicSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:basic:save")
    public String basicSave(SysBasicLightVo basicLightVo){
        return sysBasicLightService.save(basicLightVo);
    }

    @ApiOperation("移动端更新安装路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/basicUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:basic:update")
    public String basicUpdate(SysBasicLightVo basicLightVo){
        return sysBasicLightService.update(basicLightVo);
    }

    @ApiOperation("移动端删除安装路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/basicDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:basic:delete")
    public String basicDelete(@RequestParam(value = "ids[]") String[] ids){
        return sysBasicLightService.deleteBatch(ids);
    }


    @ApiOperation("通过条件查询安装路灯结果集")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "lightBrand",
                    dataType = "String", value = "安装路灯品牌"),
            @ApiImplicitParam(paramType = "query", name = "lightPower",
                    dataType = "Int", value = "安装路灯功率"),
            @ApiImplicitParam(paramType = "query", name = "lightLife",
                    dataType = "Int", value = "安装路灯寿命(h)"),
            @ApiImplicitParam(paramType = "query", name = "lightTemperature",
                    dataType = "Int", value = "安装路灯的温度(大于最大安装路灯的温度，或者小于最小安装路灯的温度)"),
            @ApiImplicitParam(paramType = "query", name = "lightVoltage",
                    dataType = "Int", value = "安装路灯的输入电压(大于最大安装路灯的输入电压，或者小于最小安装路灯的输入电压)"),
            @ApiImplicitParam(paramType = "query", name = "lightCurrent",
                    dataType = "Int", value = "安装路灯的输入电流(大于最大安装路灯的输入电流，或者小于最小安装路灯的输入电流)"),
            @ApiImplicitParam(paramType = "query", name = "lightHumidity",
                    dataType = "Int", value = "安装路灯的湿度(大于最大安装路灯的湿度，或者小于最小安装路灯的湿度)"),
            @ApiImplicitParam(paramType = "query", name = "lightColorTemperature",
                    dataType = "Int", value = "安装路灯的色温(大于最大安装路灯的色温，或者小于最小安装路灯的色温)"),
            @ApiImplicitParam(paramType = "query", name = "lightType",
                    dataType = "String", value = "安装路灯的型号(模糊查询)"),
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
    @RequiresPermissions("sys:basic:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params) {
        return sysBasicLightService.queryWithCondition(params);
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
