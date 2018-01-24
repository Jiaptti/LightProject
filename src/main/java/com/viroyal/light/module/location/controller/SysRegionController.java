package com.viroyal.light.module.location.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.location.entity.SysRegion;
import com.viroyal.light.module.location.service.ISysRegionService;
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
 * 前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
@Api("SysRegionController相关api")
@Controller
@RequestMapping("/region")
public class SysRegionController {
    @Autowired
    ISysRegionService sysRegionService;


    @ApiOperation("获得城市列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getCityList() {
       return sysRegionService.queryAllCity();
    }


    @ApiOperation("获得区列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/areaList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getAreaList() {
        return sysRegionService.queryAllArea();
    }

    @ApiOperation("获得街道列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/streetList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getStreetList() {
        return sysRegionService.queryAllStreet();
    }

    @ApiOperation("添加地区")
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @RequestMapping(value = "/regionSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:save")
    public String saveRegion(SysRegion region) {
       return sysRegionService.save(region);
    }

    @ApiOperation("添加街道")
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @RequestMapping(value = "/saveStreet", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:save")
    public String saveStreet(SysRegion region) {
        return sysRegionService.saveStreet(region);
    }

    @ApiOperation("删除地区")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @RequestMapping(value = "/regionDelete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:delete")
    public String deleteRegion(@RequestParam(value = "ids[]") String[] ids) {
        return sysRegionService.deleteBatch(ids);
    }

    @ApiOperation("更新地区")
    @ApiResponses({
            @ApiResponse(code = 200, message = "更新成功"),
            @ApiResponse(code = 500, message = "更新失败")
    })
    @RequestMapping(value = "/regionUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:update")
    public String updateRegion(SysRegion region) {
        return sysRegionService.update(region);
    }

    @ApiOperation("通过区id查询街道")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "areaId", dataType = "Int", required = true, value = "地区id")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/getAreaStreet", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getAreaStreet(@RequestParam("areaId") String areaId) {
        return sysRegionService.queryStreetByArea(Long.parseLong(areaId));
    }

    @ApiOperation("通过城市id查询区")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "cityId", dataType = "String", required = true, value = "城市id")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/getAreaByCity", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getAreaByCity(@RequestParam("cityId") String cityId) {
       return sysRegionService.queryAreaByCity(Long.parseLong(cityId));
    }

    @ApiOperation("通过条件查询所有街道(可以随意搭配，也可以单独使用，也可以一个不用),sort进行排序asc升序desc降序，pageId,pageSize为必填项" +
            "param参数接口填一个1就行，请求的时候，不需要带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageId",
                    dataType = "Int", value = "第几页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize",
                    dataType = "Int", value = "多少条"),
            @ApiImplicitParam(paramType = "query", name = "cityId",
                    dataType = "Int", value = "城市id(common_region_id)"),
            @ApiImplicitParam(paramType = "query", name = "areaId",
                    dataType = "Int", value = "区id(common_region_id)"),
            @ApiImplicitParam(paramType = "query", name = "userId",
                    dataType = "Int", value = "用户id"),
            @ApiImplicitParam(paramType = "query", name = "streetName",
                    dataType = "Int", value = "街道名(模糊查询)"),
            @ApiImplicitParam(paramType = "query", name = "sort",
                    dataType = "String", value = "排序方式"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 500, message = "查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params) {
        return sysRegionService.queryWithCondition(params);
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
