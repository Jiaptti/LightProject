package com.viroyal.light.module.location.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
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
     * @return  json格式的城市列表
     */
    String queryAllCity();

    /**
     * 查询所有区
     * @return  json格式的区列表
     */
    String queryAllArea();

    /**
     * 查询所有街道
     * @return  json格式的街道列表
     */
    String queryAllStreet();

    /**
     * 通过区查询所有街道
     * @param areaId 区Id
     * @return  json格式的街道列表
     */
    String queryStreetByArea(Long areaId);
    /**
     * 通过城市查询所有区
     * @param cityId 区Id
     * @return  json格式的区列表
     */
    String queryAreaByCity(Long cityId);
    /**
     * 存储地区
     * @param sysRegion 地区对象
     * @return json格式的结果集
     */
    String save(SysRegion sysRegion);
    /**
     * 存储街道
     * @param sysRegion 街道对象
     * @return json格式的结果集
     */
    String saveStreet(SysRegion sysRegion);

    /**
     * 更新地区
     * @param sysRegion 地区对象
     * @return json格式的结果集
     */
    String update(SysRegion sysRegion);

    /**
     * 删除地区
     * @param ids 地区对象数组
     * @return json格式的结果集
     */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件查询查询所有街道
     * @param params  查询参数
     * @return json格式的街道列表
     */
    String queryWithCondition(Map<String,Object> params);
}
