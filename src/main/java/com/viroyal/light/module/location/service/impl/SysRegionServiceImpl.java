package com.viroyal.light.module.location.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.location.entity.SysRegion;
import com.viroyal.light.module.location.dao.SysRegionMapper;
import com.viroyal.light.module.location.service.ISysRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements ISysRegionService {
    @Autowired
    SysRegionMapper sysRegionMapper;

    @Override
    public String queryAllCity() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> cityList = sysRegionMapper.queryAllCity();
        if (cityList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, cityList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryAllArea() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> areaList = sysRegionMapper.queryAllArea();
        if (areaList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, areaList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryAllStreet() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SysRegion> areaList = sysRegionMapper.queryAllStreet();
        if (areaList.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, areaList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryStreetByArea(Long areaId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(areaId == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.AREA_ID_NOT_NULL);
        } else {
            List<SysRegion> streetList = sysRegionMapper.queryStreetByArea(areaId);
            if (streetList.size() > 0) {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.VALUE_LIST, streetList);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } else {
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryAreaByCity(Long cityId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(cityId == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.CITY_ID_NOT_NULL);
        } else {
            List<SysRegion> streetList = sysRegionMapper.queryAreaByCity(cityId);
            if (streetList.size() > 0) {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.VALUE_LIST, streetList);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } else {
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String save(SysRegion sysRegion) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(StringUtils.isBlank(sysRegion.getCommonRegionId())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(sysRegion.getCommonRegionId(),6,10)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.REGION_COMMON_ID_LENGTH, 6, 10));
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(sysRegion.getRegionName())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_NAME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(sysRegion.getRegionName(),3,25)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.REGION_NAME_LENGTH, 3, 25));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getUpRegionId()) && !CommonUtil.rightLength(sysRegion.getUpRegionId(),6,10)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.REGION_UP_REGION_ID_LENGTH, 6, 10));
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(sysRegion.getRegionDesc())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_DESC_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.isRightRegionDesc(sysRegion.getRegionDesc())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_DESC_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getPostalcode()) && !CommonUtil.isRightPostalCode(sysRegion.getPostalcode())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_POSTAL_CODE_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysRegionMapper.save(sysRegion);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String saveStreet(SysRegion sysRegion) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(StringUtils.isBlank(sysRegion.getCommonRegionId())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_STREET_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(sysRegion.getCommonRegionId(),6,10)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.REGION_STREET_ID_LENGTH, 6, 10));
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(sysRegion.getRegionName())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_STRETT_NAME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(sysRegion.getRegionName(),3,25)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.REGION_STREET_NAME_LENGTH, 3, 25));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getPostalcode()) && !CommonUtil.isRightPostalCode(sysRegion.getPostalcode())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_POSTAL_CODE_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else {
            try {
                Map<String, Object> params = new LinkedHashMap<String, Object>();
                params.put("areaId", sysRegion.getCommonRegionId());
                params.put("streetName", sysRegion.getRegionName());
                SysRegion region = sysRegionMapper.queryStreet(params);
                if(region != null){
                    resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                    resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_STREET_EXIST);
                } else {
                    resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                    int size =sysRegionMapper.queryStreetCount(Long.parseLong(sysRegion.getCommonRegionId())) + 1;
                    sysRegion.setUpRegionId(sysRegion.getCommonRegionId());
                    if(size == 0){
                        sysRegion.setCommonRegionId(sysRegion.getCommonRegionId() + "0");
                    } else {
                        sysRegion.setCommonRegionId(sysRegion.getCommonRegionId() + size);
                    }
                    sysRegion.setRegionDesc("街道");
                    sysRegionMapper.save(sysRegion);
                    resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
                }
            } catch (Exception e) {
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysRegion sysRegion) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(sysRegion.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getCommonRegionId()) && !CommonUtil.rightLength(sysRegion.getCommonRegionId(),6,10)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.REGION_STREET_ID_LENGTH, 6, 10));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getRegionName()) && !CommonUtil.rightLength(sysRegion.getRegionName(),3,25)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.REGION_STREET_NAME_LENGTH, 3, 25));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getRegionDesc()) && !CommonUtil.isRightRegionDesc(sysRegion.getRegionDesc())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.REGION_DESC_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getUpRegionId()) && !CommonUtil.rightLength(sysRegion.getUpRegionId(),6,10)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.REGION_STREET_UP_REGION_ID_LENGTH, 6, 10));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(sysRegion.getPostalcode()) && !CommonUtil.isRightPostalCode(sysRegion.getPostalcode())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.REGION_POSTAL_CODE_RIGHT_FORMAT);
            return JSON.toJSONString(resultMap);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysRegionMapper.update(sysRegion);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e) {
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
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
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysRegionMapper.deleteBatch(ids);
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
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Page<SysRegion> page = new Page<SysRegion>();
        DatePage<SysRegion> datePage = null;
        int pageId = 0, pageSize = 0;
        if ((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))) {
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
                List<SysRegion> data = sysRegionMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysRegionMapper.queryWithCondition(params, page));
            }
            datePage = new DatePage<SysRegion>(page);
            datePage.setCode(BaseConstant.SUCCESS_CODE);
            datePage.setMessage(BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(datePage);
    }
}
