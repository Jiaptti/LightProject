package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysBasicLightPole;
import com.viroyal.light.module.light.dao.SysBasicLightPoleMapper;
import com.viroyal.light.module.light.entity.vo.SysBasicLightPoleVo;
import com.viroyal.light.module.light.service.ISysBasicLightPoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 灯杆基础信息表 服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Service
public class SysBasicLightPoleServiceImpl extends ServiceImpl<SysBasicLightPoleMapper, SysBasicLightPole> implements ISysBasicLightPoleService {
    @Autowired
    SysBasicLightPoleMapper sysBasicLightPoleMapper;

    @Transactional
    @Override
    public String save(SysBasicLightPoleVo lightPoleVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(StringUtils.isBlank(lightPoleVo.getPoleBrand())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_POLE_BRAND_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightPoleVo.getPoleType())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_POLE_TYPE_NOT_NULL);
            return JSON.toJSONString(resultMap);
        }  else{
            SysBasicLightPole basicLightPole = new SysBasicLightPole();
            CommonUtil.copyProperties(basicLightPole, lightPoleVo, new String[]{"exist"});
            sysBasicLightPoleMapper.save(basicLightPole);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysBasicLightPoleVo lightPoleVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(lightPoleVo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(CommonUtil.checkObjFieldIsNull(lightPoleVo)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        }else{
            SysBasicLightPole basicLightPole = new SysBasicLightPole();
            CommonUtil.copyProperties(basicLightPole, lightPoleVo, new String[]{"exist"});
            sysBasicLightPoleMapper.update(basicLightPole);
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
            sysBasicLightPoleMapper.deleteBatch(ids);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DataPage<SysBasicLightPole> dataPage = null;
        Page<SysBasicLightPole> page = new Page<SysBasicLightPole>();
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
                List<SysBasicLightPole> data = sysBasicLightPoleMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysBasicLightPoleMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysBasicLightPole>(page);
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
