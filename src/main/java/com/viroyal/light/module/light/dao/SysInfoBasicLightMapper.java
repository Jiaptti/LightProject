package com.viroyal.light.module.light.dao;

import com.viroyal.light.module.light.entity.SysInfoBasicLight;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  路灯信息和路灯关联接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-02-02
 */
public interface SysInfoBasicLightMapper extends BaseMapper<SysInfoBasicLight> {
    /**
     * 添加路灯信息和路灯关联
     * @param  infoLightList 路灯信息和路灯对象
     */
    void save(List<SysInfoBasicLight> infoLightList);

    /**
     * 更新路灯信息和路灯关联
     * @param  infoBasicLight 路灯信息和路灯对象
     * @return 更新记录条数
     */
    int update(SysInfoBasicLight infoBasicLight);

    /**
     * 删除路灯信息和路灯关联
     * @param  ids 路灯信息和路灯关联id数组
     */
    void deleteBatch(Object[] ids);
}