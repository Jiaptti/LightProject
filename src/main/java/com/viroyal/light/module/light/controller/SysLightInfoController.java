package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.viroyal.light.module.light.service.ISysLightInfoService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Api("SysLightInfoController相关api")
@Controller
@RequestMapping(value = "/lightInfo")
public class SysLightInfoController {

    @Autowired
    ISysLightInfoService sysLightInfoService;


    @ApiOperation("移动端查询所有路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/lightInfoList", method=RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String lightInfoList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryAllLight();
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端通过城市id查询所有街道")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="cityId",dataType="String",required=true,value="城市id(common_region_id)")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryCityLight", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryCityLight(@RequestParam("cityId") String cityId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByCity(Long.valueOf(cityId.substring(0,3)));
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端通过区id查询所有街道")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="areaId",dataType="String",required=true,value="区id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryAreaLight", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryAreaLight(@RequestParam("areaId") String areaId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByArea(Long.valueOf(areaId));
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端通过街道id查询所有街道")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="streetId",dataType="String",required=true,value="街道id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryStreetLight", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryByStreet( String streetId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByStreet(Long.valueOf(streetId));
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端通过组id查询所有街道")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="groupId",dataType="String",required=true,value="组id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryGroupLight", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryByGroup(@RequestParam("groupId") String groupId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByGroup(Long.valueOf(groupId));
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端获得指定维修员负责的所有路灯")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="userId",dataType="String",required=true,value="维修员id(即userId)")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryUserLight", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryByUser(@RequestParam("userId") String userId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByUser(Long.valueOf(userId));
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端获得是否启用的路灯")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="status",dataType="String",required=true, value="1表示启用，0表示没启用")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryStatusLight", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryByStatus(@RequestParam("status") String status){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByStatus(Long.valueOf(status));
        if (lightInfoList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, lightInfoList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端通过id获得指定路灯")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="id",dataType="String",required=true,value="路灯id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/getLightInfo", method =RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:info")
    public String getLightInfo(@RequestParam("id") String id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysLightInfo lightInfo = sysLightInfoService.selectById(String.valueOf(id));
        if (lightInfo != null && lightInfo.getExist() == 1) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.LIGHT_INFO, lightInfo);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    @ApiOperation("移动端添加路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="添加成功"),
            @ApiResponse(code=500,message="添加失败")
    })
    @RequestMapping(value = "/lightInfoSave", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:save")
    public String saveLightInfo(SysLightInfo lightInfo){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysLightInfoService.saveLightInfo(lightInfo);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端删除路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code=500,message="删除失败")
    })
    @RequestMapping(value = "/lightInfoDelete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:delete")
    public String deleteLightInfo(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysLightInfoService.deleteLightInfo(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端更新路灯")
    @ApiResponses({
            @ApiResponse(code=200,message="更新成功"),
            @ApiResponse(code=500,message="更新失败")
    })
    @RequestMapping(value = "/lightInfoUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:update")
    public String updateLightInfo(SysLightInfo lightInfo){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysLightInfoService.updateLightInfo(lightInfo);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    @ApiOperation("移动端通过条件查询所有路灯(标记required的就是必填)pageId,pageSize为必填项,param参数接口填一个1就行，请求的时候不需要带")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name="pageId", dataType="Int", value="第几页"),
            @ApiImplicitParam(paramType="query", name="pageSize", dataType="Int", value="多少条"),
            @ApiImplicitParam(paramType="query", name="areaId", dataType="Long", value="区Id(common_region_id)"),
            @ApiImplicitParam(paramType="query", name="areaName", dataType="String", value="区名(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="cityId", dataType="Long", value="城市Id(common_region_id)"),
            @ApiImplicitParam(paramType="query", name="streetId", dataType="Long", value="街道Id(common_region_id)"),
            @ApiImplicitParam(paramType="query", name="streetName", dataType="String", value="街道名(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="userId", dataType="Long", value="用户Id"),
            @ApiImplicitParam(paramType="query", name="userName", dataType="String", value="维修员名字"),
            @ApiImplicitParam(paramType="query", name="groupId", dataType="Long", value="组Id"),
            @ApiImplicitParam(paramType="query", name="code", dataType="String", value="路灯编号(模糊查询)"),
            @ApiImplicitParam(paramType="query", name="status", dataType="String", value="是否启用(1表示启用，0表示没启用)"),
            @ApiImplicitParam(paramType="query", name="longitude", dataType="Float", value="经度"),
            @ApiImplicitParam(paramType="query", name="latitude", dataType="Float", value="维度"),
            @ApiImplicitParam(paramType="query", name="strategyId", dataType="Float", value="决策id，具体看sys_strategy表"),
            @ApiImplicitParam(paramType="query", name="voltageThresholdGt", dataType="Int", value="电压报警阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="voltageThresholdLt", dataType="Int", value="电压报警阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="currentThresholdGt", dataType="Int", value="电流报警阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="currentThresholdLt", dataType="Int", value="电流报警阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="temperatureThresholdGt", dataType="Int", value="温度报警阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="temperatureThresholdLt", dataType="Int", value="温度报警阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="humidityThresholdGt", dataType="Int", value="湿度报警阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="humidityThresholdLt", dataType="Int", value="湿度报警阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="lightnessThresholdGt", dataType="Int", value="亮度报警阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="lightnessThresholdLt", dataType="Int", value="亮度报警阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="voltageOverloadGt", dataType="Int", value="电压过载阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="voltageOverloadLt", dataType="Int", value="电压过载阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="currentOverloadGt", dataType="Int", value="电流过载阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="currentOverloadLt", dataType="Int", value="电流过载阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="temperatureOverloadGt", dataType="Int", value="温度过载阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="temperatureOverloadLt", dataType="Int", value="温度过载阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="humidityOverloadGt", dataType="Int", value="湿度过载阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="humidityOverloadLt", dataType="Int", value="湿度过载阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="lightnessOverloadGt", dataType="Int", value="亮度过载阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="lightnessOverloadLt", dataType="Int", value="亮度过载阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="lightnessOverloadGt", dataType="Int", value="亮度过载阀值(这里表示大于)"),
            @ApiImplicitParam(paramType="query", name="lightnessOverloadLt", dataType="Int", value="亮度过载阀值(这里表示小于)"),
            @ApiImplicitParam(paramType="query", name="sort", dataType="String" ,value="排序方式(asc升序，desc降序)")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="查询成功"),
            @ApiResponse(code=500,message="查询失败")
    })
    @RequestMapping(value = "/queryWithCondition", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryWithCondition(@RequestParam Map<String, Object> params){
        if((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))){
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }
        DatePage<SysLightInfo> datePage = sysLightInfoService.queryWithCondition(params);
        return JSON.toJSONString(datePage);
    }
}
