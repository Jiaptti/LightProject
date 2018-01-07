package com.viroyal.light.module.location.dao;

import com.viroyal.light.module.location.entity.SysAreaStreet;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface SysAreaStreetMapper extends BaseMapper<SysAreaStreet> {
    /**
     * 更新街道与区的关联
     * @param areaStreet 区和街道的关联
     */
    void updateAreaStreet(SysAreaStreet areaStreet);
}