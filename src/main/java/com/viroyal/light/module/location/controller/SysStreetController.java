package com.viroyal.light.module.location.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.location.entity.SysStreet;
import com.viroyal.light.module.location.service.ISysStreetService;
import com.viroyal.light.module.user.entity.page.CustomPage;
import com.viroyal.light.module.user.entity.page.FrontPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  街道前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */

@Controller
@RequestMapping(value = "/street")
public class SysStreetController {
	@Autowired
    ISysStreetService sysStreetService;

    //移动端获得街道列表
    @RequestMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("sys:street:list")
    public String streetList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysStreet> streetList = sysStreetService.queryStreet();
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


    //移动端获得区下的街道
    @RequestMapping(value = "/getAreaStreet")
    @ResponseBody
    @RequiresPermissions("sys:street:info")
    public String getAreaStreet(String areaId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysStreet> streetList = sysStreetService.getStreetByAreaId(Long.valueOf(areaId));
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


    //移动端获得城市下的街道
    @RequestMapping(value = "/getCityStreet")
    @ResponseBody
    @RequiresPermissions("sys:street:info")
    public String getCityStreet(String cityId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysStreet> streetList = sysStreetService.getStreetByCityId(Long.valueOf(cityId));
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

    //移动端添加街道
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:street:save")
    public String saveStreet(SysStreet street){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysStreetService.saveStreet(street);
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
    @RequiresPermissions("sys:street:delete")
    public String deleteStreet(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysStreetService.deleteStreet(ids);
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
    @RequiresPermissions("sys:street:update")
    public String updateStreet(SysStreet street){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysStreetService.updateStreet(street);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    // 移动端关键字分页查询街道列表
    @RequestMapping(value = "/getStreetPage")
    @RequiresPermissions("sys:street:list")
    @ResponseBody
    public String areaPage(String keyWords, int pageSize, int pageId, String sord) {
        FrontPage<SysStreet> page = new FrontPage<SysStreet>();
        page.setSord(sord);
        page.setPage(pageId);
        page.setPageSize(pageSize);

        Wrapper<SysStreet> wrapper = new EntityWrapper<SysStreet>();
        if (!StringUtils.isEmpty(keyWords))
            wrapper.like("street_name", keyWords);
        Page<SysStreet> pageList = sysStreetService.selectPage(page.getPagePlus(), wrapper);
        CustomPage<SysStreet> customPage = new CustomPage<SysStreet>(pageList);
        String pages = JSON.toJSONString(customPage);
        return pages;
    }
}
