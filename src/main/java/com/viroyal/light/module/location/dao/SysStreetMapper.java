package com.viroyal.light.module.location.dao;

import com.viroyal.light.module.location.entity.SysStreet;
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
public interface SysStreetMapper extends BaseMapper<SysStreet> {
    /**
     * 添加街道
     * @param street 街道
     */
    void save(SysStreet street);

    /**
     * 删除街道
     * @param ids 街道id
     */
    void deleteBatch(Object[] ids);

    /**
     * 更新街道
     * @param street 街道
     */
    void update(SysStreet street);

    /**
     * 查询所有街道
     * @return 街道列表
     */
    List<SysStreet> queryAll();

    /**
     * 查询区下的所有街道
     * @return 街道列表
     * @param id 区id
     */
    List<SysStreet> getStreetByAreaId(Long id);

    /**
     * 查询城市下的所有街道
     * @return 街道列表
     * @param id 城市id
     */
    List<SysStreet> getStreetByCityId(Long id);
}