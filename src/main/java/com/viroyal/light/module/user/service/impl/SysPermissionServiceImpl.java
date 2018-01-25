package com.viroyal.light.module.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DataPage;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.CommonUtil;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.user.dao.SysRolePermissionMapper;
import com.viroyal.light.module.user.entity.SysPermission;
import com.viroyal.light.module.user.dao.SysPermissionMapper;
import com.viroyal.light.module.user.entity.vo.SysPermissionVo;
import com.viroyal.light.module.user.service.ISysPermissionService;
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
 * @since 2017-12-01
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public String queryAll() {
        Map<String, Object> resultMap = new HashMap<>();
        List<SysPermission> permissions = sysPermissionMapper.queryAll();
        if (permissions.size() > 0) {
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.VALUE_LIST, permissions);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } else {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.NO_QUERY_RESULT);
        }
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String queryWithCondition(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Page<SysPermission> page = new Page<SysPermission>();
        DataPage<SysPermission> dataPage = null;
        int pageId = 0, pageSize = 0;
        if((!params.containsKey("pageId") && params.containsKey("pageSize"))
                || (params.containsKey("pageId") && !params.containsKey("pageSize"))){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.QUERY_FAILURE + " : " + BaseConstant.REQUEST_ERROR);
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
                List<SysPermission> data = sysPermissionMapper.queryWithCondition(params);
                page.setSize(data.size());
                page.setTotal(data.size());
                page.setRecords(data);
            } else {
                page.setCurrent(pageId);
                page.setSize(pageSize);
                page.setRecords(sysPermissionMapper.queryWithCondition(params, page));
            }
            dataPage = new DataPage<SysPermission>(page);
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

    @Transactional
    @Override
    public String savePermission(SysPermissionVo permissionVo) {
        Map<String, Object> resultMap = new HashMap<>();
         if(StringUtils.isBlank(permissionVo.getName())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.PERMISSION_NAME_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(permissionVo.getName(),3,20)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.PERMISSION_NAME_LENGTH, 3,20));
            return JSON.toJSONString(resultMap);
        } else if(StringUtils.isBlank(permissionVo.getPerms())){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + BaseConstant.PERMISSION_PERMS_NOT_NULL);
            return JSON.toJSONString(resultMap);
        } else if(!CommonUtil.rightLength(permissionVo.getPerms(),5,30)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + String.format(BaseConstant.PERMISSION_PERMS_LENGTH, 5,30));
            return JSON.toJSONString(resultMap);
        } else {
            try {
                SysPermission permission = new SysPermission();
                CommonUtil.copyProperties(permission, permissionVo,new String[]{"url","exist"});
                //添加权限
                sysPermissionMapper.save(permission);
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE);
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysPermissionVo permissionVo) {
        Map<String, Object> resultMap = new HashMap<>();
        if(permissionVo.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_PERMISSION_ID);
        } else if(!StringUtils.isBlank(permissionVo.getName()) && !CommonUtil.rightLength(permissionVo.getName(),3,20)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.PERMISSION_NAME_LENGTH, 3,20));
            return JSON.toJSONString(resultMap);
        } else if(!StringUtils.isBlank(permissionVo.getPerms()) && !CommonUtil.rightLength(permissionVo.getPerms(),5,30)){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + String.format(BaseConstant.PERMISSION_PERMS_LENGTH, 5,30));
            return JSON.toJSONString(resultMap);
        } else {
            try {
                SysPermission permission = new SysPermission();
                CommonUtil.copyProperties(permission, permissionVo,new String[]{"url","exist"});
                sysPermissionMapper.update(permission);
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String deleteBatch(String[] ids) {
        Map<String, Object> resultMap = new HashMap<>();
        if(ids.length == 0){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + BaseConstant.NO_DELETE_ID);
        } else {
            try {
                resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
                sysPermissionMapper.deleteBatch(ids);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
            } catch (Exception e){
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(resultMap);
    }
}
