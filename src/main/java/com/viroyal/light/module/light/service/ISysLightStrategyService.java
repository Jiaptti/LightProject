package com.viroyal.light.module.light.service;

import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.light.entity.SysLightStrategy;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysLightStrategyService extends IService<SysLightStrategy> {
    /**
     * 添加路灯策略
     * @param  lightStrategy 路灯策略对象
     */
    void save(SysLightStrategy lightStrategy);

    /**
     * 更新路灯策略
     * @param  lightStrategy 路灯策略对象
     */
    void update(SysLightStrategy lightStrategy);

    /**
     * 删除路灯策略
     * @param  ids 路灯策略对象id数组
     */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件查询路灯策略
     * @param params 条件参数
     * @return 路灯策略列表
     */
    DatePage<SysLightStrategy> queryWithCondition(Map<String,Object> params);
}
