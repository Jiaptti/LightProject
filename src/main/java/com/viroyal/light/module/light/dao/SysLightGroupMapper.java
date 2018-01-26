package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysLightGroup;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
public interface SysLightGroupMapper extends BaseMapper<SysLightGroup> {
    /**
     *  添加路灯分组
     *  @param lightGroup 路灯分组对象
     * */
    void save(SysLightGroup lightGroup);

    /**
     *  更新路灯分组
     *  @param lightGroup 路灯分组对象
     * */
    void update(SysLightGroup lightGroup);

    /**
     *  删除路灯分组
     *  @param ids 路灯记录对象id数组
     * */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯分组
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯分组列表
     */
    List<SysLightGroup> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯分组
     * @param params 条件参数
     * @return 路灯分组列表
     */
    List<SysLightGroup> queryWithCondition(Map<String,Object> params);
}