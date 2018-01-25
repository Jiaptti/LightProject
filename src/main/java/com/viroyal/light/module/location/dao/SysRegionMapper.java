package com.viroyal.light.module.location.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.location.entity.SysRegion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {

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
     * 通过城市查询所有区
     * @param cityId 区Id
     * @return  区列表
     */
    List<SysRegion> queryAreaByCity(Long cityId);

    /**
     * 查询街道
     * @param params 查询参数
     * @return  街道对象
     */
    SysRegion queryStreet(Map<String, Object> params);

    /**
     * 查询区下面的指定街道
     * @param areaId 区id
     * @return  街道对象
     */
    SysRegion queryOneStreet(Long areaId);

    /**
     * 存储地区
     * @param sysRegion 地区对象
     */
    void save(SysRegion sysRegion);

    /**
     * 更新地区
     * @param sysRegion 地区对象
     * @return 更新的记录条数
     */
    int update(SysRegion sysRegion);

    /**
     * 删除地区
     * @param ids 地区对象数组
     */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询查询所有街道
     * @param param  参数
     * @param page 分页对象
     * @return 街道列表
     */
    List<SysRegion> queryWithCondition(Map<String, Object> param, Pagination page);

    /**
     * 通过条件查询查询所有街道
     * @param param  参数
     * @return 街道列表
     */
    List<SysRegion> queryWithCondition(Map<String, Object> param);
}