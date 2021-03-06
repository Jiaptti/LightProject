package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysBasicAlarm;
import com.viroyal.light.module.light.dao.SysBasicAlarmMapper;
import com.viroyal.light.module.light.entity.vo.SysBasicAlarmVo;
import com.viroyal.light.module.light.service.ISysBasicAlarmService;
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
 * 报警基准表 服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Service
public class SysBasicAlarmServiceImpl extends ServiceImpl<SysBasicAlarmMapper, SysBasicAlarm> implements ISysBasicAlarmService {

    @Autowired
    SysBasicAlarmMapper sysBasicAlarmMapper;

    @Transactional
    @Override
    public String save(SysBasicAlarmVo basicAlarmVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(basicAlarmVo.getAlarmReportTime() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_REPORT_TIME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMaxVoltage() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MAX_VOLTAGE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMinVoltage() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MIN_VOLTAGE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMaxTemperature() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MAX_TEMPERATURE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMinTemperature() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MIN_TEMPERATURE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMaxCurrent() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MAX_CURRENT_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMinCurrent() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MIN_CURRENT_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMaxHumidity() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MAX_HUMIDITY_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMinHumidity() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MIN_HUMIDITY_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMaxBrightness() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MAX_BRIGHTNESS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmMinBrightness() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_MIN_BRIGHTNESS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(basicAlarmVo.getAlarmTraffic() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_TRAFFIC_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(basicAlarmVo.getAlarmName())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.BASIC_ALARM_NAME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else{
            SysBasicAlarm basicAlarm = new SysBasicAlarm();
            CommonUtil.copyProperties(basicAlarm, basicAlarmVo, new String[]{"exist"});
            sysBasicAlarmMapper.save(basicAlarm);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysBasicAlarmVo basicAlarmVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(basicAlarmVo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(CommonUtil.checkObjFieldIsNull(basicAlarmVo)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        }else{
            SysBasicAlarm basicAlarm = new SysBasicAlarm();
            CommonUtil.copyProperties(basicAlarm, basicAlarmVo, new String[]{"exist"});
            sysBasicAlarmMapper.update(basicAlarm);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
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
            sysBasicAlarmMapper.deleteBatch(ids);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DataPage<SysBasicAlarm> dataPage = null;
        Page<SysBasicAlarm> page = new Page<SysBasicAlarm>();
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
                    }
                }
            }
            if(pageId == 0 || pageSize == 0){
                List<SysBasicAlarm> data = sysBasicAlarmMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysBasicAlarmMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysBasicAlarm>(page);
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
