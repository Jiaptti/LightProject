package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysBasicLightBox;
import com.viroyal.light.module.light.entity.vo.SysBasicLightBoxVo;
import com.viroyal.light.module.light.service.ISysBasicLightBoxService;
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
 * 灯箱基础信息表 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Api("SysBasicLightBoxController相关api")
@Controller
@RequestMapping(value = "/box")
public class SysBasicLightBoxController {
    @Autowired
    ISysBasicLightBoxService sysBasicLightBoxService;

    @ApiOperation("移动端添加路灯灯箱")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/boxSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:box:save")
    public String boxSave(SysBasicLightBoxVo basicLightBoxVo){
        return sysBasicLightBoxService.save(basicLightBoxVo);
    }

    @ApiOperation("移动端更新路灯灯箱")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code = 400,message="请求错误"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/boxUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:box:update")
    public String boxUpdate(SysBasicLightBoxVo basicLightBoxVo){
        return sysBasicLightBoxService.update(basicLightBoxVo);
    }

    @ApiOperation("移动端删除路灯灯箱")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/boxDelete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:box:delete")
    public String boxDelete(@RequestParam(value = "ids[]") String[] ids){
        return sysBasicLightBoxService.deleteBatch(ids);
    }


    @ApiOperation("通过条件查询路灯灯箱结果集")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "boxBrand",
                    dataType = "String", value = "灯箱品牌(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "boxType",
                    dataType = "String", value = "灯箱型号(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "boxSpecification",
                    dataType = "String", value = "灯箱规格(模糊查询)"),
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
    @RequiresPermissions("sys:box:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params) {
        return sysBasicLightBoxService.queryWithCondition(params);
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
