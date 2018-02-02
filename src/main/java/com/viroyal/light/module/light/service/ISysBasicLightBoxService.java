package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysBasicLightBox;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysBasicAlarmVo;
import com.viroyal.light.module.light.entity.vo.SysBasicLightBoxVo;

import java.util.Map;

/**
 * <p>
 * 灯箱基础信息表 服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
public interface ISysBasicLightBoxService extends IService<SysBasicLightBox> {
    /**
     *  添加路灯灯箱对象
     *  @param basicLightBoxVo 路灯灯箱对象
     *  @return json格式的添加结果
     * */
    String save(SysBasicLightBoxVo basicLightBoxVo);

    /**
     *  更新路灯灯箱
     *  @param basicLightBoxVo 路灯灯箱对象
     *  @return json格式的更新结果
     * */
    String update(SysBasicLightBoxVo basicLightBoxVo);

    /**
     *  删除路灯灯箱
     *  @param ids 路灯灯箱对象id数组
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
