package com.viroyal.light.module.location.service;

import com.viroyal.light.module.location.entity.SysStreet;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysStreetService extends IService<SysStreet> {
    /**
     * 添加街道
     * @param street 街道
     */
    void saveStreet(SysStreet street);

    /**
     * 删除街道
     * @param ids 街道id
     */
    void deleteStreet(Object[] ids);

    /**
     * 更新街道
     * @param street 街道
     */
    void updateStreet(SysStreet street);

    /**
     * 查询所有街道
     */
    List<SysStreet> queryStreet();

    /**
     * 查询区下的所有街道
     * @param id 区id
     */
    List<SysStreet> getStreetByAreaId(Long id);

    /**
     * 查询城市下的所有街道
     * @param id 城市id
     */
    List<SysStreet> getStreetByCityId(Long id);
}
