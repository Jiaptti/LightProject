package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysLight;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysLightVo;

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
    String save(SysLightVo light);

    /**
     * 更新路灯数据
     * @param  light 路灯数据对象
     * @return json格式的结果集
     */
    String update(SysLightVo light);

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

    /**
     * 分页路灯实时数据列表
     * @param params 分页条件
     * @return json格式的路灯实时数据列表
     */
    String queryCurrentDate(Map<String,Object> params);
}
