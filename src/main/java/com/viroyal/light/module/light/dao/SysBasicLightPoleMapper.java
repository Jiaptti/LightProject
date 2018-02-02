package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysBasicLightPole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 灯杆基础信息表 Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Mapper
public interface SysBasicLightPoleMapper extends BaseMapper<SysBasicLightPole> {

     /**  添加路灯灯杆
     *  @param lightPole 路灯灯杆对象
     * */
    void save(SysBasicLightPole lightPole);

    /**
     *  更新路灯灯杆
     *  @param basicLight 路灯灯杆对象
     * */
    void update(SysBasicLightPole basicLight);

    /**
     *  删除路灯灯杆
     *  @param ids  路灯灯杆对象id数组
     * */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯灯杆
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯灯杆列表
     */
    List<SysBasicLightPole> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯灯杆
     * @param params 条件参数
     * @return 路灯灯杆列表
     */
    List<SysBasicLightPole> queryWithCondition(Map<String,Object> params);
}