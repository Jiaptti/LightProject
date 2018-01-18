package com.viroyal.light.module.light.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.module.light.entity.SysLight;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysLightService extends IService<SysLight> {
    /**
     * 添加路灯数据
     * @param  light 路灯数据对象
     */
    void save(SysLight light);

    /**
     * 更新路灯数据
     * @param  light 路灯数据对象
     */
    void update(SysLight light);

    /**
     * 删除路灯数据
     * @param  ids 路灯数据对象id数组
     */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯数据
     * @param params 条件参数
     * @return 路灯数据列表
     */
    DatePage<SysLight> queryWithCondition(Map<String,Object> params);

}
