package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysLightRecord;
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
public interface SysLightRecordMapper extends BaseMapper<SysLightRecord> {
    /**
     *  添加路灯记录
     *  @param lightRecord 路灯记录对象
     * */
    void save(SysLightRecord lightRecord);

    /**
     *  更新路灯记录
     *  @param lightRecord 路灯记录对象
     * */
    void update(SysLightRecord lightRecord);

    /**
     *  删除路灯对象
     *  @param ids 路灯记录对象id数组
     * */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯记录
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯记录列表
     */
    List<SysLightRecord> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯记录
     * @param params 条件参数
     * @return 路灯记录列表
     */
    List<SysLightRecord> queryWithCondition(Map<String,Object> params);
}