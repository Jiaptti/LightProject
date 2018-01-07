package com.viroyal.light.module.location.service.impl;

import com.viroyal.light.module.location.dao.SysAreaStreetMapper;
import com.viroyal.light.module.location.entity.SysAreaStreet;
import com.viroyal.light.module.location.entity.SysStreet;
import com.viroyal.light.module.location.dao.SysStreetMapper;
import com.viroyal.light.module.location.service.ISysStreetService;
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
public class SysStreetServiceImpl extends ServiceImpl<SysStreetMapper, SysStreet> implements ISysStreetService {
    @Autowired
    SysStreetMapper sysStreetMapper;

    @Autowired
    SysAreaStreetMapper sysAreaStreetMapper;

    @Transactional
    @Override
    public void saveStreet(SysStreet street) {
        //添加街道
        sysStreetMapper.save(street);

        //设定区街道关联数据
        SysAreaStreet areaStreet = new SysAreaStreet();
        areaStreet.setAreaId(street.getAreaId());
        areaStreet.setStreetId(street.getId());

        //添加区街道数据
        sysAreaStreetMapper.insert(areaStreet);
    }

    @Transactional
    @Override
    public void deleteStreet(Object[] ids) {
        sysStreetMapper.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void updateStreet(SysStreet street) {
        //更新街道
        sysStreetMapper.update(street);

        //设定区街道关联数据
        SysAreaStreet areaStreet = new SysAreaStreet();
        areaStreet.setAreaId(street.getAreaId());
        areaStreet.setStreetId(street.getId());

        //更新区街道数据
        sysAreaStreetMapper.updateAreaStreet(areaStreet);
    }

    @Override
    public List<SysStreet> queryStreet() {
        return sysStreetMapper.queryAll();
    }

    @Override
    public List<SysStreet> getStreetByAreaId(Long id) {
        return sysStreetMapper.getStreetByAreaId(id);
    }

    @Override
    public List<SysStreet> getStreetByCityId(Long id) {
        return sysStreetMapper.getStreetByCityId(id);
    }
}
