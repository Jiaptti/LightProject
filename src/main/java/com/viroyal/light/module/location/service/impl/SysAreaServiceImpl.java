package com.viroyal.light.module.location.service.impl;

import com.viroyal.light.module.location.dao.SysCityAreaMapper;
import com.viroyal.light.module.location.entity.SysArea;
import com.viroyal.light.module.location.dao.SysAreaMapper;
import com.viroyal.light.module.location.entity.SysCityArea;
import com.viroyal.light.module.location.service.ISysAreaService;
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
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements ISysAreaService {

    @Autowired
    SysAreaMapper sysAreaMapper;

    @Autowired
    SysCityAreaMapper sysCityAreaMapper;


    @Transactional
    @Override
    public void saveArea(SysArea area) {
        //添加区
        sysAreaMapper.save(area);

        //设置城市和区关联对象
        SysCityArea cityArea = new SysCityArea();
        cityArea.setCityId(area.getCityId());
        cityArea.setAreaId(area.getId());

        //添加城市和区关联
        sysCityAreaMapper.insert(cityArea);
    }

    @Transactional
    @Override
    public void deleteArea(Object[] ids) {
        sysAreaMapper.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void updateArea(SysArea area) {
        sysAreaMapper.update(area);

        //设置城市和区关联对象
        SysCityArea cityArea = new SysCityArea();
        cityArea.setCityId(area.getCityId());
        cityArea.setAreaId(area.getId());

        //更新城市和区关联
        sysCityAreaMapper.update(cityArea,null);
    }

    @Override
    public List<SysArea> queryAllArea() {
        return sysAreaMapper.queryAll();
    }

    @Override
    public List<SysArea> getAreaByCityId(Long cityId) {
        return sysAreaMapper.getAreaByCityId(cityId);
    }
}
