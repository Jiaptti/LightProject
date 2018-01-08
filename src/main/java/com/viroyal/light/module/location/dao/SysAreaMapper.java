package com.viroyal.light.module.location.dao;

import com.viroyal.light.module.location.entity.SysArea;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface SysAreaMapper extends BaseMapper<SysArea> {

    /**
     * 添加区
     * @param area 区
     */
    void save(SysArea area);

    /**
     * 删除区
     * @param ids  区id数组
     */
    void deleteBatch(Object[] ids);


    /**
     * 更新区
     * @param area 区
     */
    void update(SysArea area);

    /**
     * 获得所有区
     * @return 区列表
     */
    List<SysArea> queryAll();

    /**
     * 获得所有区
     * @return 区列表
     * @param cityId 城市id
     */
    List<SysArea> getAreaByCityId(Long cityId);
}