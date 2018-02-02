package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysBasicLightPole;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysBasicLightPoleVo;

import java.util.Map;

/**
 * <p>
 * 灯杆基础信息表 服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
public interface ISysBasicLightPoleService extends IService<SysBasicLightPole> {
    /**
     *  添加路灯灯杆对象
     *  @param lightPoleVo 路灯灯杆对象
     *  @return json格式的添加结果
     * */
    String save(SysBasicLightPoleVo lightPoleVo);

    /**
     *  更新路灯灯杆
     *  @param lightPoleVo 路灯灯杆对象
     *  @return json格式的更新结果
     * */
    String update(SysBasicLightPoleVo lightPoleVo);

    /**
     *  删除路灯灯杆
     *  @param ids 路灯灯杆对象id数组
     *  @return json格式的删除结果
     * */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件查询路灯灯箱
     * @param params 条件参数
     * @return json格式的路灯灯箱列表
     */
    String queryWithCondition(Map<String,Object> params);
}
