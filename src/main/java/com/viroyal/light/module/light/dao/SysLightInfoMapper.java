package com.viroyal.light.module.light.dao;

import com.viroyal.light.module.light.entity.SysLightInfo;
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
public interface SysLightInfoMapper extends BaseMapper<SysLightInfo> {
    /**
     * 查询所有路灯
     */
    List<SysLightInfo> queryAll();

    /**
     * 查询指定城市的路灯
     * @param cityId 城市id
     */
    List<SysLightInfo> queryByCity(Long cityId);

    /**
     * 查询指定区的路灯
     * @param areaId 区id
     */
    List<SysLightInfo> queryByArea(Long areaId);

    /**
     * 查询指定街道的路灯
     * @param streetId 街道id
     */
    List<SysLightInfo> queryByStreet(Long streetId);

    /**
     * 查询指定组的路灯
     * @param groupId 组id
     */
    List<SysLightInfo> queryByGroup(Long groupId);

    /**
     * 查询由谁维修的所有路灯
     * @param userId 维修员id
     */
    List<SysLightInfo> queryByUser(Long userId);

    /**
     * 得到是否投入使用的路灯
     * @param status 使用状态
     */
    List<SysLightInfo> queryByStatus(String status);

    /**
     * 增加路灯
     * @param lightInfo 路灯
     */
    void save(SysLightInfo lightInfo);

    /**
     * 删除路灯
     * @param ids 删除路灯的id集合
     */
    void deleteBatch(Object[] ids);

    /**
     * 更新路灯
     * @param lightInfo 路灯
     */
    void update(SysLightInfo lightInfo);
}