package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysLightInfo;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysLightInfoVo;
import org.springframework.cache.annotation.CacheEvict;

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
public interface ISysLightInfoService extends IService<SysLightInfo> {

    /**
     * 通过各种条件查询
     * @param params 条件参数
     * @return json格式的路灯信息列表
     */
    String queryWithCondition(Map<String, Object> params);

    /**
     * 增加路灯
     * @param lightInfo 路灯
     * @return json格式的结果集
     */
    String saveLightInfo(SysLightInfoVo lightInfo);

    /**
     * 删除路灯
     * @param ids 删除路灯的id集合
     * @return json格式的结果集
     */
    String deleteLightInfo(Object[] ids);

    /**
     * 更新路灯
     * @param lightInfo 路灯
     * @return json格式的结果集
     */
    String updateLightInfo(SysLightInfoVo lightInfo);

    /**
     * 将路灯进行分组
     * @param groupId 路灯分组id
     * @param infoIds 路灯info的id列表，用逗号隔开
     * @return json格式的结果集
     */
    String dispatchLightToGroup(String groupId, String infoIds);

    /**
     * 给多个组的路灯进行添加策略
     * @param  strategyId 决策id
     * @param  groupId 分组id
     * @return json格式的结果集
     */
    @CacheEvict(value = "lightInfo", allEntries=true)
    String dispatchGroupStrategy(String strategyId, String groupId);

    /**
     * 给多个路灯添加策略
     * @param  strategyId 决策id
     * @param  lightInfoIds 要分配的路灯id
     * @return json格式的结果集
     */
    String dispatchStrategy(String strategyId, String lightInfoIds);
}
