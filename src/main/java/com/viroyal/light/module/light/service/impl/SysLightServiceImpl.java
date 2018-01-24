package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysLight;
import com.viroyal.light.module.light.dao.SysLightMapper;
import com.viroyal.light.module.light.service.ISysLightService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Service
public class SysLightServiceImpl extends ServiceImpl<SysLightMapper, SysLight> implements ISysLightService {

    @Autowired
    SysLightMapper sysLightMapper;

    @Transactional
    @Override
    public String save(SysLight light) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(light.getStatus() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_STATUS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getVoltage() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_VOLTAGE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getCurrent() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_CURRENT_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getTrafficFlow() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_TRAFFIC_FLOW_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getTemperature() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_TEMPERATURE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getHumidity() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_HUMIDITY_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getLightness() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_LIGHTNESS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(light.getInfoId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.LIGHT_INFO_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                light.setDatetime(new Date());
                sysLightMapper.save(light);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysLight light) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(light.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                light.setLastUpdateTime(new Date());
                sysLightMapper.update(light);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String deleteBatch(Object[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(ids.length == 0){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + BaseConstant.NO_DELETE_ID);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysLightMapper.deleteBatch(ids);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DataPage<SysLight> dataPage = null;
        Page<SysLight> page = new Page<SysLight>();
        int pageId = 0, pageSize = 0;
        if((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))){
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
        } else {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if(entry.getKey().toString().equals("pageId")){
                    pageId = Integer.parseInt(entry.getValue().toString());
                } else if(entry.getKey().toString().equals("pageSize")){
                    pageSize = Integer.parseInt(entry.getValue().toString());
                } else {
                    if(NumberUtils.isNumber(entry.getValue().toString())){
                        params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                    }
                }
            }
            if(pageId == 0 || pageSize == 0){
                List<SysLight> data = sysLightMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysLightMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysLight>(page);
            if(dataPage.getRecords() == 0){
                dataPage.setCode(BaseConstant.ERROR_CODE);
                dataPage.setMessage(BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
            } else {
                dataPage.setCode(BaseConstant.SUCCESS_CODE);
                dataPage.setMessage(BaseConstant.SUCCESS_RESULT);
            }
        }
        return JSON.toJSONString(dataPage);
    }
}
