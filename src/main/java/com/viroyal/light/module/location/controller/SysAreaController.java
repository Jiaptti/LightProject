package com.viroyal.light.module.location.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.location.entity.SysArea;
import com.viroyal.light.module.location.service.ISysAreaService;
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
 *  区前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Controller
@RequestMapping(value = "/area")
public class SysAreaController {

    @Autowired
    ISysAreaService sysAreaService;

    //移动端获得区列表
    @RequestMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("sys:area:list")
    public String areaList(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysArea> areaList = sysAreaService.queryAllArea();
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

    //移动端获得指定的区
    @RequestMapping(value = "/getArea")
    @ResponseBody
    @RequiresPermissions("sys:area:list")
    public String areaList(@RequestParam(value = "id")String id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SysArea area = sysAreaService.selectById(id);
        if (area != null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.AREA, area);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }


    //移动端获得城市下面的区
    @RequestMapping(value = "/getCityArea")
    @ResponseBody
    @RequiresPermissions("sys:area:list")
    public String getCityArea(String cityId){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysArea> areaList = sysAreaService.getAreaByCityId(Long.valueOf(cityId));
        if (areaList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            resultMap.put(BaseConstant.VALUE_LIST, areaList);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE);
        }
        return JSON.toJSONString(resultMap);
    }

    //移动端添加区
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:area:save")
    public String saveArea(SysArea area){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(area.getCityId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_AREA_CITY_ERROR);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysAreaService.saveArea(area);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    //删除区
    @RequestMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("sys:area:delete")
    public String deleteArea(@RequestParam(value = "ids[]") String[] ids){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysAreaService.deleteArea(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e){
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }


    //移动端更新区
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:area:update")
    public String updateArea(SysArea area){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(area.getCityId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_AREA_CITY_ERROR);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysAreaService.updateArea(area);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    // 移动端关键字分页查询城市列表
    @RequestMapping(value = "/getAreaPage")
    @RequiresPermissions("sys:area:list")
    @ResponseBody
    public String areaPage(String keyWords, int pageSize, int pageId, String sord) {
        FrontPage<SysArea> page = new FrontPage<SysArea>();
        page.setSord(sord);
        page.setPage(pageId);
        page.setPageSize(pageSize);

        Wrapper<SysArea> wrapper = new EntityWrapper<SysArea>();
        if (!StringUtils.isEmpty(keyWords))
            wrapper.like("area_name", keyWords);
        Page<SysArea> pageList = sysAreaService.selectPage(page.getPagePlus(), wrapper);
        DatePage<SysArea> customPage = new DatePage<SysArea>(pageList);
        return JSON.toJSONString(customPage);
    }

}
