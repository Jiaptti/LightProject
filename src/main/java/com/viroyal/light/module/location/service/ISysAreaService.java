package com.viroyal.light.module.location.service;

import com.viroyal.light.module.location.entity.SysArea;
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
public interface ISysAreaService extends IService<SysArea> {

    /**
     * 添加区
     * @param area 区
     */
    void saveArea(SysArea area);

    /**
     * 删除区
     * @param ids  区id数组
     */
    void deleteArea(Object[] ids);


    /**
     * 更新区
     * @param area 区
     */
    void updateArea(SysArea area);

    /**
     * 获得所有区
     */
    List<SysArea> queryAllArea();

    /**
     * 获得所有区
     * @param cityId 城市id
     */
    List<SysArea> getAreaByCityId(Long cityId);
}
