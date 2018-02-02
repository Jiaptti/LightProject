package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysBasicLight;
import com.viroyal.light.module.light.entity.SysLightInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Mapper
@CacheConfig(cacheNames = "sysLightInfo")
public interface SysLightInfoMapper extends BaseMapper<SysLightInfo> {
    /**
     * 增加路灯
     * @param lightInfo 路灯
     */
    @CacheEvict(value = "lightInfo", allEntries=true)
    void save(SysLightInfo lightInfo);

    /**
     * 删除路灯
     * @param ids 删除路灯的id集合
     */
    @CacheEvict(value = "lightInfo", allEntries=true)
    void deleteBatch(Object[] ids);

    /**
     * 更新路灯
     * @param lightInfo 路灯
     * @return 更新的记录条数
     */
    @CacheEvict(value = "lightInfo", allEntries=true)
    int update(SysLightInfo lightInfo);

    /**
     * 通过条件分页查询路灯
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯信息列表
     */
    @Cacheable(value = "lightInfo")
    List<SysLightInfo> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯
     * @param params 条件参数
     * @return 路灯信息列表
     */
    @Cacheable(value = "lightInfo")
    List<SysLightInfo> queryWithCondition(Map<String,Object> params);

    /**
     * 将路灯分到指定的组
     * @param  sysLightInfoList 路灯信息列表对象
     */
    @CacheEvict(value = "lightInfo", allEntries=true)
    void updateBatch(List<SysLightInfo> sysLightInfoList);

    /**
     * 通过路灯信息id获得所有安装路灯
     * @param  infoId 路灯信息id
     * @return 安装路灯列表
     */
    List<SysBasicLight> getLightById(Long infoId);

    /**
     * 清除所有路灯分组
     * @param groupId 要清除的组id
     */
    void updateGroupBatch(Long groupId);


    /**
     * 给指定街道的路灯添加策略
     * @param sysLightInfoList 路灯信息列表对象
     */
    void updateBatchByStreet(List<SysLightInfo> sysLightInfoList);

    /**
     * 给多个路灯添加策略
     * @param  sysLightInfoList 路灯分组对象
     */
    void dispatchStrategy(List<SysLightInfo> sysLightInfoList);
}