package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysLight;
import com.baomidou.mybatisplus.service.IService;

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
     * @return json格式的结果集
     */
    String save(SysLight light);

    /**
     * 更新路灯数据
     * @param  light 路灯数据对象
     * @return json格式的结果集
     */
    String update(SysLight light);

    /**
     * 删除路灯数据
     * @param  ids 路灯数据对象id数组
     * @return json格式的结果集
     */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯数据
     * @param params 条件参数
     * @return json格式的路灯数据列表
     */
    String queryWithCondition(Map<String,Object> params);

}
