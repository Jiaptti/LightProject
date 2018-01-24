package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.viroyal.light.module.light.dao.SysLightInfoMapper;
import com.viroyal.light.module.light.service.ISysLightInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysLightInfoServiceImpl extends ServiceImpl<SysLightInfoMapper, SysLightInfo> implements ISysLightInfoService {

    @Autowired
    SysLightInfoMapper sysLightInfoMapper;

    @Transactional
    @Override
    public String saveLightInfo(SysLightInfo lightInfo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(StringUtils.isBlank(lightInfo.getCode())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_CODE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightInfo.getLightInfo())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightInfo.getStatus())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_STATUS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightInfo.getLongitude() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_LONGITUDE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightInfo.getLatitude() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_LATITUDE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightInfo.getStreetId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_STREET_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysLightInfoMapper.save(lightInfo);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String deleteLightInfo(Object[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(ids.length == 0){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + BaseConstant.NO_DELETE_ID);
        } else {
            try{
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysLightInfoMapper.deleteBatch(ids);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String updateLightInfo(SysLightInfo lightInfo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(lightInfo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
        } else {
            try{
                if(sysLightInfoMapper.update(lightInfo) == 0){
                    resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                    resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
                } else {
                    resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                    resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
                }

            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
            }
        }

        return JSON.toJSONString(resultMap);
    }


    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DataPage<SysLightInfo> dataPage = null;
        Page<SysLightInfo> page = new Page<SysLightInfo>();
        int pageId = 0, pageSize = 0;
        if((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))){
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        } else {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if(entry.getKey().toString().equals("pageId")){
                    pageId = Integer.parseInt(entry.getValue().toString());
                } else if(entry.getKey().toString().equals("pageSize")){
                    pageSize = Integer.parseInt(entry.getValue().toString());
                } else {
                    if(NumberUtils.isNumber(entry.getValue().toString())){
                        params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                        if(entry.getKey().toString().equals("cityId")){
                            params.put(entry.getKey(), Long.valueOf(entry.getValue().toString().substring(0,3)));
                        }
                    }
                }
            }
            if(pageId == 0 || pageSize == 0){
                List<SysLightInfo> data = sysLightInfoMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysLightInfoMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysLightInfo>(page);
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
