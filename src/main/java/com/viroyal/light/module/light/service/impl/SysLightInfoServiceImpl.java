package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.viroyal.light.module.light.dao.SysLightInfoMapper;
import com.viroyal.light.module.light.entity.vo.SysLightInfoVo;
import com.viroyal.light.module.light.service.ISysLightInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public String saveLightInfo(SysLightInfoVo lightInfoVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(StringUtils.isBlank(lightInfoVo.getCode())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_CODE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightInfoVo.getLightInfo())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightInfoVo.getStatus())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_STATUS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightInfoVo.getLongitude() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_LONGITUDE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightInfoVo.getLatitude() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_LATITUDE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightInfoVo.getStreetId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_INFO_STREET_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else {
            try{
                SysLightInfo lightInfo = new SysLightInfo();
                CommonUtil.copyProperties(lightInfo, lightInfoVo,
                        new String[]{"strategyCloseTime","strategyOpenTime","closeTime","openTime","exist"});
                sysLightInfoMapper.save(lightInfo);
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
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
    public String updateLightInfo(SysLightInfoVo lightInfoVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(lightInfoVo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightInfoVo.getCode()) && StringUtils.isBlank(lightInfoVo.getLightInfo())
                && StringUtils.isBlank(lightInfoVo.getStatus()) && lightInfoVo.getLongitude() == null
                && lightInfoVo.getLatitude() == null && lightInfoVo.getAutoReport() == null
                && lightInfoVo.getCurrentOverload() == null && lightInfoVo.getGroupId() == null
                && lightInfoVo.getCurrentThreshold() == null && lightInfoVo.getHumidityOverload() == null
                && lightInfoVo.getLightnessOverload() == null && lightInfoVo.getLightnessThreshold() == null
                && StringUtils.isBlank(lightInfoVo.getLightInfo()) && lightInfoVo.getTemperatureOverload() == null
                && lightInfoVo.getStrategyId()==null && lightInfoVo.getUserId() == null
                && lightInfoVo.getVoltageOverload() == null && lightInfoVo.getVoltageThreshold() == null
                && lightInfoVo.getTemperatureThreshold() == null && lightInfoVo.getHumidityThreshold() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        } else {
            try{
                SysLightInfo lightInfo = new SysLightInfo();
                CommonUtil.copyProperties(lightInfo, lightInfoVo,
                        new String[]{"strategyCloseTime","strategyOpenTime","closeTime","openTime","exist"});
                sysLightInfoMapper.update(lightInfo);
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String dispatchLightToGroup(String groupId, String infoIds) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(groupId) || StringUtils.isEmpty(infoIds)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_GROUP_SUCCESS + " : " + BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }try {
            List<SysLightInfo> infoList = new ArrayList<>();
            String[] ids = infoIds.split(",");
            sysLightInfoMapper.updateGroupBatch(Long.parseLong(groupId));
            if (ids.length > 1) {
                for (int i = 0; i < ids.length; i++) {
                    SysLightInfo sysLightInfo = new SysLightInfo();
                    sysLightInfo.setId(Long.parseLong(ids[i]));
                    sysLightInfo.setGroupId(Long.valueOf(groupId));
                    infoList.add(sysLightInfo);
                }
            } else {
                SysLightInfo sysLightInfo = new SysLightInfo();
                sysLightInfo.setId(Long.parseLong(infoIds));
                sysLightInfo.setGroupId(Long.valueOf(groupId));
                infoList.add(sysLightInfo);
            }
            sysLightInfoMapper.updateBatch(infoList);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_GROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_GROUP_FAILUER + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String dispatchGroupStrategy(String strategyId, String groupId) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(strategyId) || StringUtils.isEmpty(groupId)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_STRATEGY_FAILUER + " : " + BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }try {
            List<SysLightInfo> infoList = new ArrayList<>();
            String[] ids = groupId.split(",");
//            sysLightInfoMapper.updateStrategyBatch(Long.parseLong(strategyId));
            if (ids.length > 1) {
                for (int i = 0; i < ids.length; i++) {
                    SysLightInfo sysLightInfo = new SysLightInfo();
                    sysLightInfo.setStrategyId(Long.valueOf(strategyId));
                    sysLightInfo.setGroupId(Long.valueOf(ids[i]));
                    infoList.add(sysLightInfo);
                }
            } else {
                SysLightInfo sysLightInfo = new SysLightInfo();
                sysLightInfo.setStrategyId(Long.valueOf(strategyId));
                sysLightInfo.setGroupId(Long.valueOf(groupId));
                infoList.add(sysLightInfo);
            }
            sysLightInfoMapper.updateBatchByGroup(infoList);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_STRATEGY_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_STRATEGY_FAILUER + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String dispatchStrategy(String strategyId, String lightInfoIds) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(strategyId) || StringUtils.isEmpty(lightInfoIds)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_FAILUER + " : " + BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }try {
            List<SysLightInfo> infoList = new ArrayList<>();
            String[] ids = lightInfoIds.split(",");
            if (ids.length > 1) {
                for (int i = 0; i < ids.length; i++) {
                    SysLightInfo sysLightInfo = new SysLightInfo();
                    sysLightInfo.setStrategyId(Long.valueOf(strategyId));
                    sysLightInfo.setId(Long.valueOf(ids[i]));
                    infoList.add(sysLightInfo);
                }
            } else {
                SysLightInfo sysLightInfo = new SysLightInfo();
                sysLightInfo.setStrategyId(Long.valueOf(strategyId));
                sysLightInfo.setId(Long.valueOf(lightInfoIds));
                infoList.add(sysLightInfo);
            }
            sysLightInfoMapper.dispatchStrategy(infoList);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_FAILUER + " : " + e.getMessage());
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
