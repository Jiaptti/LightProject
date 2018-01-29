package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysLight;
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
@CacheConfig(cacheNames = "sysLight")
public interface SysLightMapper extends BaseMapper<SysLight> {

    /**
     * 添加路灯数据
     * @param  light 路灯数据对象
     */
    @CacheEvict(value = "light", allEntries=true)
    void save(SysLight light);

    /**
     * 更新路灯数据
     * @param  light 路灯数据对象
     */
    @CacheEvict(value = "light", allEntries=true)
    int update(SysLight light);

    /**
     * 删除路灯数据
     * @param  ids 路灯数据对象id数组
     */
    @CacheEvict(value = "light", allEntries=true)
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯数据
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯数据列表
     */
    @Cacheable(value = "light")
    List<SysLight> queryWithCondition(Map<String,Object> params, Pagination page);

    /**
     * 通过条件查询路灯数据
     * @param params 条件参数
     * @return 路灯数据列表
     */
    @Cacheable(value = "light")
    List<SysLight> queryWithCondition(Map<String,Object> params);

    /**
     * 获得分页实时路灯数据列表
     * @return 实时路灯数据列表
     */
    List<SysLight> queryCurrentDate(Map<String,Object> params, Pagination page);

    /**
     * 获得实时路灯数据列表
     * @return 实时路灯数据列表
     */
    List<SysLight> queryCurrentDate(Map<String,Object> params);
}