package com.viroyal.light.module.location.service.impl;

import com.viroyal.light.module.location.entity.SysRegion;
import com.viroyal.light.module.location.dao.SysRegionMapper;
import com.viroyal.light.module.location.service.ISysRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
