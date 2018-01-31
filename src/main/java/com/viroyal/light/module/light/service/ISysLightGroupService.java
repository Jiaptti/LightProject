package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysLightGroup;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysLightGroupVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysLightGroupService extends IService<SysLightGroup> {
    /**
     *  添加路灯分组
     *  @param lightGroupVo 路灯分组对象
     *  @return json格式的添加结果
     * */
    String save(SysLightGroupVo lightGroupVo);

    /**
     *  更新路灯分组
     *  @param lightGroupVo 路灯分组对象
     *  @return json格式的更新结果
     * */
    String update(SysLightGroupVo lightGroupVo);

    /**
     *  删除路灯分组
     *  @param ids 路灯分组对象id数组
     *  @return json格式的删除结果
     * */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件查询路灯分组
     * @param params 条件参数
     * @return json格式的路灯分组列表
     */
    String queryWithCondition(Map<String,Object> params);

    /**
     * 给路灯分组添加策略
     * @param groupId 路灯分组id
     * @param strategyId 决策id
     * @return json格式的结果集
     */
    String dispatchStrategy(String groupId, String strategyId);
}
