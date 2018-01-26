package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysLightRecord;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysLightRecordVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysLightRecordService extends IService<SysLightRecord> {
    /**
     *  添加路灯记录
     *  @param lightRecordVo 路灯记录对象
     *  @return json格式的添加路灯记录结果
     * */
    String save(SysLightRecordVo lightRecordVo);

    /**
     *  更新路灯记录
     *  @param lightRecordVo 路灯记录对象
     *  @return json格式的更新路灯记录结果
     * */
    String update(SysLightRecordVo lightRecordVo);

    /**
     *  删除路灯对象
     *  @param ids 路灯记录对象id数组
     *  @return json格式的删除路灯记录结果
     * */
    String deleteBatch(Object[] ids);

    /**
     * 通过条件查询路灯记录
     * @param params 条件参数
     * @return json格式的路灯记录列表
     */
    String queryWithCondition(Map<String,Object> params);
}
