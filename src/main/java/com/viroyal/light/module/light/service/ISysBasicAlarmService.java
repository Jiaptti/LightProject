package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysBasicAlarm;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysBasicAlarmVo;

import java.util.Map;

/**
 * <p>
 * 报警基准表 服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
public interface ISysBasicAlarmService extends IService<SysBasicAlarm> {
    /**
     *  添加路灯警报对象
     *  @param basicAlarmVo 路灯警报对象
     *  @return json格式的添加结果
     * */
    String save(SysBasicAlarmVo basicAlarmVo);

    /**
     *  更新路灯分组
     *  @param basicAlarmVo 路灯警报对象
     *  @return json格式的更新结果
     * */
    String update(SysBasicAlarmVo basicAlarmVo);

    /**
     *  删除路灯警报
     *  @param ids 路灯警报对象id数组
     *  @return json格式的删除结果
     * */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件查询路灯警报
     * @param params 条件参数
     * @return json格式的路灯警报列表
     */
    String queryWithCondition(Map<String,Object> params);
}
