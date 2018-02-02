package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysBasicLight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 安装路灯基础信息表 Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Mapper
public interface SysBasicLightMapper extends BaseMapper<SysBasicLight> {
    /**
     *  添加安装路灯
     *  @param basicLight 安装路灯对象
     * */
    void save(SysBasicLight basicLight);

    /**
     *  更新安装路灯
     *  @param basicLight 安装路灯对象
     * */
    void update(SysBasicLight basicLight);

    /**
     *  删除安装路灯
     *  @param ids  安装路灯对象id数组
     * */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询安装路灯
     * @param params 条件参数
     * @param page 分页条件
     * @return 安装路灯列表
     */
    List<SysBasicLight> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询安装路灯
     * @param params 条件参数
     * @return 安装路灯列表
     */
    List<SysBasicLight> queryWithCondition(Map<String,Object> params);
}