package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysLightRecord;
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
@CacheConfig(cacheNames = "sysLightRecord")
public interface SysLightRecordMapper extends BaseMapper<SysLightRecord> {
    /**
     *  添加路灯记录
     *  @param lightRecord 路灯记录对象
     * */
    @CacheEvict(value = "lightRecord", allEntries=true)
    void save(SysLightRecord lightRecord);

    /**
     *  更新路灯记录
     *  @param lightRecord 路灯记录对象
     * */
    @CacheEvict(value = "lightRecord", allEntries=true)
    void update(SysLightRecord lightRecord);

    /**
     *  删除路灯对象
     *  @param ids 路灯记录对象id数组
     * */
    @CacheEvict(value = "lightRecord", allEntries=true)
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯记录
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯记录列表
     */
    @Cacheable(value = "lightRecord")
    List<SysLightRecord> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯记录
     * @param params 条件参数
     * @return 路灯记录列表
     */
    @Cacheable(value = "lightRecord")
    List<SysLightRecord> queryWithCondition(Map<String,Object> params);
}