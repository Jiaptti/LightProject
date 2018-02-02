package com.viroyal.light.module.light.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.module.light.entity.SysBasicAlarm;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 报警基准表 Mapper 接口
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Mapper
public interface SysBasicAlarmMapper extends BaseMapper<SysBasicAlarm> {
    /**
     *  添加路灯警报
     *  @param basicAlarm 路灯警报对象
     * */
    void save(SysBasicAlarm basicAlarm);

    /**
     *  更新路灯警报
     *  @param basicAlarm 路灯警报对象
     * */
    void update(SysBasicAlarm basicAlarm);

    /**
     *  删除路灯警报
     *  @param ids  路灯警报对象id数组
     * */
    void deleteBatch(Object[] ids);

    /**
     * 通过条件分页查询路灯警报
     * @param params 条件参数
     * @param page 分页条件
     * @return 路灯警报列表
     */
    List<SysBasicAlarm> queryWithCondition(Map<String,Object> params, Pagination page);


    /**
     * 通过条件查询路灯警报
     * @param params 条件参数
     * @return 路灯警报列表
     */
    List<SysBasicAlarm> queryWithCondition(Map<String,Object> params);
}