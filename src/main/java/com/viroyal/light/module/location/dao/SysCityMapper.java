package com.viroyal.light.module.location.dao;

import com.viroyal.light.module.location.entity.SysCity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface SysCityMapper extends BaseMapper<SysCity> {
    /**
     * 查询城市
     * @return 城市列表
     */
    List<SysCity> queryAll();

    /**
     * 添加城市
     * @param city  城市
     */
    void save(SysCity city);

    /**
     * 删除城市
     * @return 删除的城市记录
     * @param ids  城市id数组
     */
    int deleteBatch(Object[] ids);


    /**
     * 更新城市城市
     * @param city  城市id数组
     */
    void update(SysCity city);


}