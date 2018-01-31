package com.viroyal.light.module.light.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.common.utils.ShiroUtils;
import com.viroyal.light.module.light.entity.SysLightGroup;
import com.viroyal.light.module.light.dao.SysLightGroupMapper;
import com.viroyal.light.module.light.entity.vo.SysLightGroupVo;
import com.viroyal.light.module.light.service.ISysLightGroupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Service
public class SysLightGroupServiceImpl extends ServiceImpl<SysLightGroupMapper, SysLightGroup> implements ISysLightGroupService {
	@Autowired
    SysLightGroupMapper sysLightGroupMapper;

    @Override
    public String save(SysLightGroupVo lightGroupVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(lightGroupVo.getGroupId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_GROUP_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(lightGroupVo.getResponsibleId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_GROUP_RESPONSE_ID_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(lightGroupVo.getGroupName())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.LIGHT_GROUP_NAME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else{
            SysLightGroup sysLightGroup = new SysLightGroup();
            CommonUtil.copyProperties(sysLightGroup, lightGroupVo, new String[]{"createUserId","createTime","lastUpdateTime",
                    "lastUpdateUserId","exist"});
            sysLightGroup.setCreateTime(new Date());
            sysLightGroup.setCreateUserId(ShiroUtils.getUserId());
            sysLightGroupMapper.save(sysLightGroup);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String update(SysLightGroupVo lightGroupVo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(lightGroupVo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(lightGroupVo.getResponsibleId() == null && lightGroupVo.getGroupId() == null
                && StringUtils.isBlank(lightGroupVo.getGroupName()) && lightGroupVo.getGroupStrategyId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        }else{
            SysLightGroup sysLightGroup = new SysLightGroup();
            CommonUtil.copyProperties(sysLightGroup, lightGroupVo, new String[]{"createUserId","createTime","lastUpdateTime",
                    "lastUpdateUserId","exist"});
            sysLightGroup.setLastUpdateTime(new Date());
            sysLightGroup.setLastUpdateUserId(ShiroUtils.getUserId());
            sysLightGroupMapper.update(sysLightGroup);

            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String deleteBatch(Object[] ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(ids.length == 0){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.NO_DELETE_ID);
        } else {
            sysLightGroupMapper.deleteBatch(ids);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryWithCondition(Map<String, Object> params) {
        DataPage<SysLightGroup> dataPage = null;
        Page<SysLightGroup> page = new Page<SysLightGroup>();
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
                List<SysLightGroup> data = sysLightGroupMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysLightGroupMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysLightGroup>(page);
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

    @Override
    public String dispatchStrategy(String groupId, String strategyId) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(groupId) || StringUtils.isEmpty(strategyId)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_FAILUER + " : " + BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }try {
            List<SysLightGroup> infoList = new ArrayList<>();
            String[] ids = groupId.split(",");
            if (ids.length > 1) {
                for (int i = 0; i < ids.length; i++) {
                    SysLightGroup sysLightGroup = new SysLightGroup();
                    sysLightGroup.setGroupStrategyId(Long.valueOf(strategyId));
                    sysLightGroup.setId(Long.valueOf(ids[i]));
                    infoList.add(sysLightGroup);
                }
            } else {
                SysLightGroup sysLightGroup = new SysLightGroup();
                sysLightGroup.setGroupStrategyId(Long.valueOf(strategyId));
                sysLightGroup.setId(Long.valueOf(groupId));
                infoList.add(sysLightGroup);
            }
            sysLightGroupMapper.dispatchStrategy(infoList);
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LIGHT_INFO_DISPATCH_FAILUER + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
