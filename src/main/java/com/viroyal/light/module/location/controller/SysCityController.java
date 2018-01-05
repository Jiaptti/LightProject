package com.viroyal.light.module.location.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.location.entity.SysCity;
import com.viroyal.light.module.location.service.ISysCityService;
import com.viroyal.light.module.user.entity.page.CustomPage;
import com.viroyal.light.module.user.entity.page.FrontPage;
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
@RequestMapping(value = "/city")
public class SysCityController {

    @Autowired
    ISysCityService sysCityService;


    //移动端获得城市列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:city:list")
    public String getCityList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysCity> cityList = sysCityService.queryAllCity();
        if (cityList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        resultMap.put(BaseConstant.VALUE_LIST, cityList);
        return JSON.toJSONString(resultMap);
    }


    //移动端添加城市
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:city:save")
    public String saveCity(SysCity city){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysCityService.saveCity(city);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    //移动端删除城市
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:city:delete")
    public String deleteCity(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysCityService.deleteCityBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    //移动端更新城市
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:city:update")
    public String updateCity(SysCity city){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysCityService.updateCity(city);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    //移动端获得单个城市
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys:city:info")
    public String getCity(@RequestParam("id") String id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysCity city = sysCityService.selectById(Long.valueOf(id));
        if (city != null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        resultMap.put(BaseConstant.CITY, city);
        return JSON.toJSONString(resultMap);
    }


    // 移动端关键字分页查询城市列表
    @RequestMapping(value = "/getCityPage")
    @RequiresPermissions("sys:city:list")
    @ResponseBody
    public String cityPage(int pageSize, int pageId, String sord, String keyWords) {
        FrontPage<SysCity> page = new FrontPage<SysCity>();
        if(pageId == 0){
            pageId = 1;
        }
        if(pageSize == 0) {
            pageSize = 5;
        }
        if(!StringUtils.isEmpty(sord)){
            page.setSord(sord);
        }
        page.setPage(pageId);
        page.setPageSize(pageSize);

        Wrapper<SysCity> wrapper = new EntityWrapper<SysCity>();
        if (!StringUtils.isEmpty(keyWords))
            wrapper.like("city_name", keyWords);
        Page<SysCity> pageList = sysCityService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysCity> customPage = new CustomPage<SysCity>(pageList);
        String pages = JSON.toJSONString(customPage);
        return pages;
    }

}
