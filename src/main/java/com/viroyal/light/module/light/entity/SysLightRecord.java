package com.viroyal.light.module.light.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 路灯记录实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@TableName("sys_light_record")
public class SysLightRecord extends Model<SysLightRecord> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 记录时间
     */
	@TableField("record_date")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date recordDate;
    /**
     * 记录状态
     */
	@TableField("record_status")
	private String recordStatus;
    /**
     * 记录操作
     */
	@TableField("record_operation")
	private String recordOperation;
    /**
     * 谁进行了操作
     */
	@TableField("record_user_id")
	private Long recordUserId;

    /**
     * 对应sys_light_info里的id
     */
	@TableField("light_info_id")
	private String lightInfoId;

	/**
	 * 最后更新的用户id
	 * 对应sys_user_id里的id
	 */
	@TableField("last_update_user_id")
	private Long lastUpdateUserId;

	/**
	 * 最后更新时间
	 */
	@TableField("last_update_time")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;


	/**
	 * 是否可见
	 */
	@TableField("exist")
	private Integer exist;

	@TableField(exist = false)
	private String nickName;

	@TableField(exist = false)
	private String code;

	@TableField(exist = false)
	private String lightInfo;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
