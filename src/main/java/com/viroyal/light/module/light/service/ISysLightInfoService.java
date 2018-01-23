package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysLightInfo;
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
    String saveLightInfo(SysLightInfo lightInfo);

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
    String updateLightInfo(SysLightInfo lightInfo);
}
