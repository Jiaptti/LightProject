package com.viroyal.light.module.light.service.impl;

import com.viroyal.light.module.light.entity.SysLightInfo;
import com.viroyal.light.module.light.dao.SysLightInfoMapper;
import com.viroyal.light.module.light.service.ISysLightInfoService;
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
}
