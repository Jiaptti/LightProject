package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.module.light.entity.SysInfoBasicLight;
import com.viroyal.light.module.light.dao.SysInfoBasicLightMapper;
import com.viroyal.light.module.light.entity.vo.SysInfoBasicLightVo;
import com.viroyal.light.module.light.service.ISysInfoBasicLightService;
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
 * @since 2018-02-02
 */
@Service
public class SysInfoBasicLightServiceImpl extends ServiceImpl<SysInfoBasicLightMapper, SysInfoBasicLight> implements ISysInfoBasicLightService {

    @Autowired
    SysInfoBasicLightMapper sysInfoBasicLightMapper;

    @Transactional
    @Override
    public String save(String infoId, String basicId) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(infoId)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }
        try {
            if(!StringUtils.isBlank(basicId)){
                List<SysInfoBasicLight> infoBasicLightList = new ArrayList<>();
                String[] ids = basicId.split(",");
                if (ids.length > 1) {
                    for (int i = 0; i < ids.length; i++) {
                        SysInfoBasicLight basicLight = new SysInfoBasicLight();
                        basicLight.setInfoId(Long.valueOf(infoId));
                        basicLight.setBasicId(Long.valueOf(ids[i]));
                        infoBasicLightList.add(basicLight);
                    }
                } else {
                    if(!StringUtils.equals(basicId, "0")){
                        SysInfoBasicLight basicLight = new SysInfoBasicLight();
                        basicLight.setInfoId(Long.valueOf(infoId));
                        basicLight.setBasicId(Long.valueOf(basicId));
                        infoBasicLightList.add(basicLight);
                    }
                }
                if(infoBasicLightList.size() > 0){
                    sysInfoBasicLightMapper.save(infoBasicLightList);
                }
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysInfoBasicLightVo infoBasicLightVo) {
        Map<String, Object> resultMap = new HashMap<>();
        if(infoBasicLightVo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(CommonUtil.checkObjFieldIsNull(infoBasicLightVo)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        }
        try {
            SysInfoBasicLight basicLight = new SysInfoBasicLight();
            CommonUtil.copyProperties(basicLight, infoBasicLightVo, new String[]{"exist"});
            sysInfoBasicLightMapper.update(basicLight);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String deleteBatch(Object[] ids) {
        Map<String, Object> resultMap = new HashMap<>();
        if (ids.length == 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " +BaseConstant.NO_DELETE_ID);
            return JSON.toJSONString(resultMap);
        }
        try {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            sysInfoBasicLightMapper.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
