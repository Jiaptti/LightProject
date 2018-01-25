package com.viroyal.light.module.user.service;

import com.viroyal.light.module.user.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;
import com.viroyal.light.module.user.entity.vo.SysRoleVo;

import java.util.Map;

/**
 * <p>
 *  角色服务类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 添加角色
     * @param role 角色对象
     * @return json格式的结果
     */
    String save(SysRoleVo role);

    /**
     * 更新角色
     * @param role 角色对象
     * @return json格式的结果
     */
    String update(SysRoleVo role);

    /**
     * 删除角色
     * @param ids 角色对象id数组
     * @return json格式的结果
     */
    String deleteBatch(Object[] ids);

    /**
     * 分页查询角色
     * @param params 查询条件
     * @return json格式的结果集合
     */
    String queryWithCondition(Map<String, Object> params);
}
