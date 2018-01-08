package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.viroyal.light.module.light.entity.SysUserLight;
import com.viroyal.light.module.light.service.ISysLightInfoService;
import com.viroyal.light.module.light.service.ISysUserLightService;
import com.viroyal.light.common.page.CustomPage;
import com.viroyal.light.common.page.FrontPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping(value = "/lightInfo")
public class SysLightInfoController {

    @Autowired
    ISysLightInfoService sysLightInfoService;

    @Autowired
    ISysUserLightService sysUserLightService;


    //移动端获得路灯列表
    @RequestMapping(value = "/list")
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


    //移动端获得城市下的所有路灯
    @RequestMapping(value = "/queryCityLight")
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:list")
    public String queryCityLight(@RequestParam("cityId") String cityId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysLightInfo> lightInfoList = sysLightInfoService.queryByCity(Long.valueOf(cityId));
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


    //移动端获得区下的所有路灯
    @RequestMapping(value = "/queryAreaLight")
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

    //移动端获得街道下的所有路灯
    @RequestMapping(value = "/queryStreetLight")
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


    //移动端获得指定组下的所有路灯
    @RequestMapping(value = "/queryGroupLight")
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


    //移动端获得指定维修员负责的所有路灯
    @RequestMapping(value = "/queryUserLight")
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

    //移动端获得是否启用的路灯
    @RequestMapping(value = "/queryStatusLight")
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

    //移动端获得单个路灯
    @RequestMapping(value = "/getLightInfo")
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:info")
    public String getLightInfo(@RequestParam("id") String id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysLightInfo lightInfo = sysLightInfoService.selectById(String.valueOf(id));
        if (lightInfo != null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.LIGHT_INFO, lightInfo);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    //移动端添加街道
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:save")
    public String saveStreet(SysLightInfo lightInfo){
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

    //移动指派路灯由谁负责
    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:lightInfo:info")
    public String dispatchLight(SysUserLight userLight){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysUserLightService.dispatchToUser(userLight);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    //删除街道
    @RequestMapping(value = "/delete")
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
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    //移动端更新街道
    @RequestMapping(value = "/update", method = RequestMethod.POST)
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
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    // 移动端关键字分页查询街道列表
    @RequestMapping(value = "/getLightInfoPage")
    @RequiresPermissions("sys:lightInfo:list")
    @ResponseBody
    public String areaPage(int pageSize, int pageId, String sord,String keyWords) {
        FrontPage<SysLightInfo> page = new FrontPage<SysLightInfo>();
        page.setSord(sord);
        page.setPage(pageId);
        page.setPageSize(pageSize);

        Wrapper<SysLightInfo> wrapper = new EntityWrapper<SysLightInfo>();
        if (!StringUtils.isEmpty(keyWords))
            wrapper.like("code", keyWords);
        Page<SysLightInfo> pageList = sysLightInfoService.selectPage(page.getPagePlus(), wrapper);
        DatePage<SysLightInfo> datePage = new DatePage<SysLightInfo>(pageList);
        return JSON.toJSONString(datePage);
    }
}
