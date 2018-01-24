package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysLightStrategy;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface SysLightStrategyMapper extends BaseMapper<SysLightStrategy> {
    /**
     * 添加路灯策略
     * @param  lightStrategy 路灯策略对象
     */
    void save(SysLightStrategy lightStrategy);

    /**
     * 更新路灯策略
     * @param  lightStrategy 路灯策略对象
     * @return 更新结果记录条数
     */
    int update(SysLightStrategy lightStrategy);

    /**
     * 删除路灯策略
     * @param  ids 路灯策略对象id数组
     */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯策略
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯策略列表
     */
    List<SysLightStrategy> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯策略
     * @param params 条件参数
     * @return 路灯策略列表
     */
    List<SysLightStrategy> queryWithCondition(Map<String,Object> params);
}