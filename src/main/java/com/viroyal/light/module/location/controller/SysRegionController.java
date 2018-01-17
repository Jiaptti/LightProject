package com.viroyal.light.module.location.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.location.entity.SysRegion;
import com.viroyal.light.module.location.service.ISysRegionService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
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
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getCityList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> cityList = sysRegionService.queryAllCity();
        if (cityList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, cityList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("获得区列表")
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/areaList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getAreaList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> areaList = sysRegionService.queryAllArea();
        if (areaList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, areaList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("获得街道列表")
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/streetList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getStreetList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> areaList = sysRegionService.queryAllStreet();
        if (areaList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, areaList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("添加地区")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/regionSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:save")
    public String saveRegion(SysRegion region){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysRegionService.save(region);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("删除地区")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/regionDelete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:delete")
    public String deleteRegion(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysRegionService.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("更新地区")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/regionUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:update")
    public String updateRegion(SysRegion region){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(region.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NO_REGION_ID);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysRegionService.update(region);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("通过区id查询街道")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="areaId",dataType="Int",required=true,value="地区id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/getAreaStreet", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getAreaStreet(@RequestParam("areaId") String areaId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> streetList = sysRegionService.queryStreetByArea(Long.valueOf(areaId));
        if (streetList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, streetList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("通过城市id查询区")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="cityId",dataType="String",required=true,value="城市id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/getAreaByCity", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String getAreaByCity(@RequestParam("cityId") String cityId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> streetList = sysRegionService.queryAreaByCity(Long.parseLong(cityId));
        if (streetList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, streetList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("通过条件查询所有街道(可以随意搭配，也可以单独使用，也可以一个不用),sort进行排序asc升序desc降序，pageId,pageSize为必填项" +
            "param参数接口填一个1就行，请求的时候，不需要带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageId",
                    dataType="Int", value="第几页"),
            @ApiImplicitParam(paramType="query",name="pageSize",
                    dataType="Int", value="多少条"),
            @ApiImplicitParam(paramType="query",name="cityId",
                    dataType="Int",required=false,value="城市id(common_region_id)"),
            @ApiImplicitParam(paramType="query",name="areaId",
                    dataType="Int",required=false,value="区id(common_region_id)"),
            @ApiImplicitParam(paramType="query",name="userId",
                    dataType="Int",required=false,value="用户id"),
            @ApiImplicitParam(paramType="query",name="sort",
                    dataType="String",required=false,value="排序方式"),
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:region:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        if((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))){
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }
        DatePage<SysRegion> datePage = sysRegionService.queryWithCondition(params);
        return JSON.toJSONString(datePage);
    }
}
