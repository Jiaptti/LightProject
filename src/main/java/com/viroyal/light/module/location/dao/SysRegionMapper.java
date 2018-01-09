package com.viroyal.light.module.location.dao;

import com.viroyal.light.module.location.entity.SysRegion;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    List<SysRegion> queryAllCity();

    List<SysRegion> queryAllArea();

    List<SysRegion> queryAllStreet();

    List<SysRegion> queryStreetByArea(Long areaId);

    void save(SysRegion sysRegion);

    void update(SysRegion sysRegion);

    void deleteBatch(Object[] ids);
}