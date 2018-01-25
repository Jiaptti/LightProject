package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysLightStrategy;
import com.viroyal.light.module.light.dao.SysLightStrategyMapper;
import com.viroyal.light.module.light.entity.vo.SysLightStrategyVo;
import com.viroyal.light.module.light.service.ISysLightStrategyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Service
public class SysLightStrategyServiceImpl extends ServiceImpl<SysLightStrategyMapper, SysLightStrategy> implements ISysLightStrategyService {

    @Autowired
    SysLightStrategyMapper sysLightStrategyMapper;

    @Transactional
    @Override
    public String save(SysLightStrategyVo sysLightStrategyVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (sysLightStrategyVo.getSmoothLevel() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_STRATEGY_SMOOTH_LEVEL_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if (sysLightStrategyVo.getTrafficLevel() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_STRATEGY_TRAFFIC_LEVEL_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if (StringUtils.isBlank(sysLightStrategyVo.getType())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_STRATEGY_TYPE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if (StringUtils.isBlank(sysLightStrategyVo.getOpenTime())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_STRATEGY_OPEN_TIME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if (StringUtils.isBlank(sysLightStrategyVo.getCloseTime())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_STRATEGY_CLOSE_TIME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else {
            try {

                SysLightStrategy lightStrategy = new SysLightStrategy();
                CommonUtil.copyProperties(lightStrategy, sysLightStrategyVo, new String[]{"strategyOpenTime, strategyCloseTime","exist"});
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date openDate = sdf.parse(sysLightStrategyVo.getStrategyOpenTime());
                Date closeDate = sdf.parse(sysLightStrategyVo.getStrategyCloseTime());
                lightStrategy.setStrategyOpenTime(openDate);
                lightStrategy.setStrategyCloseTime(closeDate);
                sysLightStrategyMapper.save(lightStrategy);

                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysLightStrategyVo lightStrategyVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (lightStrategyVo.getId() == null) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if (lightStrategyVo.getSmoothLevel() == null && lightStrategyVo.getTrafficLevel() == null
                && StringUtils.isBlank(lightStrategyVo.getOpenTime()) && StringUtils.isBlank(lightStrategyVo.getCloseTime())
                && StringUtils.isBlank(lightStrategyVo.getStrategyCloseTime()) && StringUtils.isBlank(lightStrategyVo.getStrategyOpenTime())) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        } else {
            try {
                SysLightStrategy lightStrategy = new SysLightStrategy();
                CommonUtil.copyProperties(lightStrategy, lightStrategyVo, new String[]{"strategyOpenTime, strategyCloseTime","exist"});
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date openDate = sdf.parse(lightStrategyVo.getStrategyOpenTime());
                Date closeDate = sdf.parse(lightStrategyVo.getStrategyCloseTime());
                lightStrategy.setStrategyOpenTime(openDate);
                lightStrategy.setStrategyCloseTime(closeDate);
                sysLightStrategyMapper.update(lightStrategy);
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
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
        if (ids.length == 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + BaseConstant.NO_DELETE_ID);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysLightStrategyMapper.deleteBatch(ids);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }


    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DataPage<SysLightStrategy> dataPage = null;
        Page<SysLightStrategy> page = new Page<SysLightStrategy>();
        int pageId = 0, pageSize = 0;
        if ((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
        } else {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getKey().toString().equals("pageId")) {
                    pageId = Integer.parseInt(entry.getValue().toString());
                } else if (entry.getKey().toString().equals("pageSize")) {
                    pageSize = Integer.parseInt(entry.getValue().toString());
                } else {
                    if (NumberUtils.isNumber(entry.getValue().toString())) {
                        params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                    }
                }
            }
            if (pageId == 0 || pageSize == 0) {
                List<SysLightStrategy> data = sysLightStrategyMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysLightStrategyMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysLightStrategy>(page);
            if (dataPage.getRecords() == 0) {
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
