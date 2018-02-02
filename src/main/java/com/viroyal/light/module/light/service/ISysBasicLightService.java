package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysBasicLight;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysBasicLightVo;

import java.util.Map;

/**
 * <p>
 * 安装路灯基础信息表 服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
public interface ISysBasicLightService extends IService<SysBasicLight> {
    /**
     *  添加安装路灯对象
     *  @param basicLightVo 安装路灯对象
     *  @return json格式的添加结果
     * */
    String save(SysBasicLightVo basicLightVo);

    /**
     *  更新安装路灯
     *  @param basicLightVo 安装路灯对象
     *  @return json格式的更新结果
     * */
    String update(SysBasicLightVo basicLightVo);

    /**
     *  删除安装路灯
     *  @param ids 安装路灯对象id数组
     *  @return json格式的删除结果
     * */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件查询安装路灯
     * @param params 条件参数
     * @return json格式的安装路灯列表
     */
    String queryWithCondition(Map<String,Object> params);
}
