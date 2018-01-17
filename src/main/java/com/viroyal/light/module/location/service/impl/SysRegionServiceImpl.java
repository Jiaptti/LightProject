package com.viroyal.light.module.location.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.location.entity.SysRegion;
import com.viroyal.light.module.location.dao.SysRegionMapper;
import com.viroyal.light.module.location.service.ISysRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
 * @since 2018-01-09
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements ISysRegionService {
    @Autowired
    SysRegionMapper sysRegionMapper;

    @Override
    public List<SysRegion> queryAllCity() {
        return sysRegionMapper.queryAllCity();
    }

    @Override
    public List<SysRegion> queryAllArea() {
        return sysRegionMapper.queryAllArea();
    }

    @Override
    public List<SysRegion> queryAllStreet() {
        return sysRegionMapper.queryAllStreet();
    }

    @Override
    public List<SysRegion> queryStreetByArea(Long areaId) {
        return sysRegionMapper.queryStreetByArea(areaId);
    }

    @Override
    public List<SysRegion> queryAreaByCity(Long cityId) {
        return sysRegionMapper.queryAreaByCity(cityId);
    }

    @Transactional
    @Override
    public void save(SysRegion sysRegion) {
        sysRegionMapper.save(sysRegion);
    }

    @Transactional
    @Override
    public void update(SysRegion sysRegion) {
        sysRegionMapper.update(sysRegion);
    }

    @Transactional
    @Override
    public void deleteBatch(Object[] ids) {
        sysRegionMapper.deleteBatch(ids);
    }

    @Override
    public DatePage<SysRegion> queryWithCondition(Map<String, Object> params) {
        Page<SysRegion> page = new Page<SysRegion>();
        int pageId = 0, pageSize = 0;
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
        return new DatePage<SysRegion>(page);
    }
}
