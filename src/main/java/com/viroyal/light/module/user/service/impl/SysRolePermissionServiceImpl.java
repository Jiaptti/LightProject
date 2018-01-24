package com.viroyal.light.module.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.user.entity.SysRolePermission;
import com.viroyal.light.module.user.dao.SysRolePermissionMapper;
import com.viroyal.light.module.user.service.ISysRolePermissionService;
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
 * 服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Transactional
    @Override
    public String save(String roleId, String permissionId) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(permissionId)) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.REQUEST_ERROR);
            return JSON.toJSONString(resultMap);
        }
        try {
            sysRolePermissionMapper.deleteAllPerms(Long.parseLong(roleId));
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            List<SysRolePermission> rolePermissionList = new ArrayList<>();
            String[] ids = permissionId.split(",");
            if (ids.length > 1) {
                for (int i = 0; i < ids.length; i++) {
                    SysRolePermission rolePermission = new SysRolePermission();
                    rolePermission.setRid(Long.valueOf(roleId));
                    rolePermission.setPid(Long.valueOf(ids[i]));
                    rolePermissionList.add(rolePermission);
                }
            } else {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setRid(Long.valueOf(roleId));
                rolePermission.setPid(Long.valueOf(permissionId));
                rolePermissionList.add(rolePermission);
            }
            sysRolePermissionMapper.save(rolePermissionList);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SAVE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @Override
    public String update(SysRolePermission rolePermission) {
        Map<String, Object> resultMap = new HashMap<>();
        if(rolePermission.getId() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_UPDATE_ID);
            return JSON.toJSONString(resultMap);
        } else if(rolePermission.getRid() == null && rolePermission.getPid() == null){
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.UPDATE_FAILURE + " : " + BaseConstant.NO_DATA_TO_UPDATE);
            return JSON.toJSONString(resultMap);
        }
        try {
            sysRolePermissionMapper.update(rolePermission);
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
            sysRolePermissionMapper.deleteBatch(ids);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.SUCCESS_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.DELETE_FAILURE + " : " + e.getMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
