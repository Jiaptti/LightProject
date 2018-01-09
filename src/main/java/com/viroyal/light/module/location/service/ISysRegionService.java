package com.viroyal.light.module.location.service;

import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.location.entity.SysRegion;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
public interface ISysRegionService extends IService<SysRegion> {

    List<SysRegion> queryAllCity();

    List<SysRegion> queryAllArea();

    List<SysRegion> queryAllStreet();

    List<SysRegion> queryStreetByArea(Long areaId);

    void save(SysRegion sysRegion);

    void update(SysRegion sysRegion);

    void deleteBatch(Object[] ids);
}
