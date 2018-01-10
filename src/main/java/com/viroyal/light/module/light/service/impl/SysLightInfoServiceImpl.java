package com.viroyal.light.module.light.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.viroyal.light.module.light.dao.SysLightInfoMapper;
import com.viroyal.light.module.light.service.ISysLightInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<SysLightInfo> queryAllLight() {
        return sysLightInfoMapper.queryAll();
    }

    @Override
    public List<SysLightInfo> queryByCity(Long cityId) {
        return sysLightInfoMapper.queryByCity(cityId);
    }

    @Override
    public List<SysLightInfo> queryByArea(Long areaId) {
        return sysLightInfoMapper.queryByArea(areaId);
    }

    @Override
    public List<SysLightInfo> queryByStreet(Long streetId) {
        return sysLightInfoMapper.queryByStreet(streetId);
    }

    @Override
    public List<SysLightInfo> queryByGroup(Long groupId) {
        return sysLightInfoMapper.queryByGroup(groupId);
    }

    @Override
    public List<SysLightInfo> queryByUser(Long userId) {
        return sysLightInfoMapper.queryByUser(userId);
    }

    @Override
    public List<SysLightInfo> queryByStatus(Long status) {
        return sysLightInfoMapper.queryByStatus(status);
    }

    @Transactional
    @Override
    public void saveLightInfo(SysLightInfo lightInfo) {
        sysLightInfoMapper.save(lightInfo);
    }

    @Transactional
    @Override
    public void deleteLightInfo(Object[] ids) {
        sysLightInfoMapper.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void updateLightInfo(SysLightInfo lightInfo) {
        sysLightInfoMapper.update(lightInfo);
    }


    @Override
    public DatePage<SysLightInfo> queryWithCondition(Map<String, Object> params) {
        FrontPage<SysLightInfo> page = new FrontPage<SysLightInfo>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getKey().toString().equals("pageId")){
                page.setPage(Integer.parseInt(entry.getValue().toString()));
            } else if(entry.getKey().toString().equals("pageSize")){
                page.setPageSize(Integer.parseInt(entry.getValue().toString()));
            } else {
                if(!entry.getKey().toString().equals("code") && !entry.getKey().toString().equals("sort")){
                    params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                    if(entry.getKey().equals("cityId")){
                        params.put(entry.getKey(), Long.valueOf(entry.getValue().toString().substring(0,3)));
                    }
                }
            }
        }
        RowBounds rowBounds = new RowBounds(page.getPage() - 1, page.getPageSize());
        page.setData(sysLightInfoMapper.queryWithCondition(params, rowBounds));
        return new DatePage<SysLightInfo>(page.getPagePlus());
    }
}
