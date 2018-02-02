package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysBasicLightBox;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 灯箱基础信息表 Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Mapper
public interface SysBasicLightBoxMapper extends BaseMapper<SysBasicLightBox> {
    /**
     *  添加路灯灯箱
     *  @param lightBox 路灯灯箱对象
     * */
    void save(SysBasicLightBox lightBox);

    /**
     *  更新路灯灯箱
     *  @param lightBox 路灯灯箱对象
     * */
    void update(SysBasicLightBox lightBox);

    /**
     *  删除路灯灯箱
     *  @param ids  路灯灯箱对象id数组
     * */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯灯箱
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯灯箱列表
     */
    List<SysBasicLightBox> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯灯箱
     * @param params 条件参数
     * @return 路灯灯箱列表
     */
    List<SysBasicLightBox> queryWithCondition(Map<String,Object> params);
}