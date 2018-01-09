package com.viroyal.light.module.light.service;

import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.baomidou.mybatisplus.service.IService;

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
     * 查询所有路灯
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryAllLight();

    /**
     * 查询指定城市的路灯
     * @param cityId 城市id
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryByCity(Long cityId);

    /**
     * 查询指定区的路灯
     * @param areaId 区id
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryByArea(Long areaId);

    /**
     * 查询指定街道的路灯
     * @param streetId 街道id
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryByStreet(Long streetId);

    /**
     * 查询指定组的路灯
     * @param groupId 组id
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryByGroup(Long groupId);

    /**
     * 查询由谁维修的所有路灯
     * @param userId 维修员id
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryByUser(Long userId);

    /**
     * 得到是否投入使用的路灯
     * @param status 使用状态
     * @return 路灯信息列表
     */
    List<SysLightInfo> queryByStatus(Long status);


    /**
     * 通过各种条件查询
     * @param params 条件参数
     * @return 路灯信息列表
     */
    DatePage<SysLightInfo> queryWithCondition(Map<String, Object> params);

    /**
     * 增加路灯
     * @param lightInfo 路灯
     */
    void saveLightInfo(SysLightInfo lightInfo);

    /**
     * 删除路灯
     * @param ids 删除路灯的id集合
     */
    void deleteLightInfo(Object[] ids);

    /**
     * 更新路灯
     * @param lightInfo 路灯
     */
    void updateLightInfo(SysLightInfo lightInfo);
}
