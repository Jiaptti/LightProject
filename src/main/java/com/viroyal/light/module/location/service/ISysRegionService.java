package com.viroyal.light.module.location.service;

import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.location.entity.SysRegion;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
public interface ISysRegionService extends IService<SysRegion> {
    /**
     * 查询所有城市
     * @return  城市列表
     */
    List<SysRegion> queryAllCity();

    /**
     * 查询所有区
     * @return  区列表
     */
    List<SysRegion> queryAllArea();

    /**
     * 查询所有街道
     * @return  街道列表
     */
    List<SysRegion> queryAllStreet();

    /**
     * 通过区查询所有街道
     * @param areaId 区Id
     * @return  街道列表
     */
    List<SysRegion> queryStreetByArea(Long areaId);

    /**
     * 存储地区
     * @param sysRegion 地区对象
     */
    void save(SysRegion sysRegion);

    /**
     * 更新地区
     * @param sysRegion 地区对象
     */
    void update(SysRegion sysRegion);

    /**
     * 删除地区
     * @param ids 地区对象数组
     */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件查询查询所有街道
     * @param param  参数可以是cityId,areaId,userId的组合
     *               eg:cityId=xx&areaId=xx
     * @return 街道列表
     */
    DatePage<SysRegion> queryWithCondition(Map<String, Object> param);
}
