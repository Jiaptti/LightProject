package com.viroyal.light.module.location.service.impl;

import com.viroyal.light.module.location.entity.SysCity;
import com.viroyal.light.module.location.dao.SysCityMapper;
import com.viroyal.light.module.location.service.ISysCityService;
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
public class SysCityServiceImpl extends ServiceImpl<SysCityMapper, SysCity> implements ISysCityService {

    @Autowired
    SysCityMapper sysCityMapper;


    @Override
    public List<SysCity> queryAllCity() {
        return sysCityMapper.queryAll();
    }

    @Transactional
    @Override
    public void saveCity(SysCity city) {
        sysCityMapper.save(city);
    }

    @Transactional
    @Override
    public int deleteCityBatch(Object[] ids) {
        return sysCityMapper.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void updateCity(SysCity city) {
        sysCityMapper.update(city);
    }
}
