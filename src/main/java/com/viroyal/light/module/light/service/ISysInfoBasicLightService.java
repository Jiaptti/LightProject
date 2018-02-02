package com.viroyal.light.module.light.service;

import com.viroyal.light.module.light.entity.SysInfoBasicLight;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.light.entity.vo.SysInfoBasicLightVo;

/**
 * <p>
 *  路灯信息和路灯关联服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-02-02
 */
public interface ISysInfoBasicLightService extends IService<SysInfoBasicLight> {
    /**
     * 添加路灯信息和路灯关联
     * @param  infoId 路灯信息id
     * @param  basicId 安装路灯id
     * @return json格式的结果
     */
    String save(String infoId, String basicId);

    /**
     * 更新路灯信息和路灯关联
     * @param  infoBasicLightVo 路灯信息和路灯关联对象
     * @return json格式的结果
     */
    String update(SysInfoBasicLightVo infoBasicLightVo);

    /**
     * 删除路灯信息和路灯关联
     * @param  ids 角色权限id数组
     * @return json格式的结果
     */
    String deleteBatch(Object[] ids);
}
