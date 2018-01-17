package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="地区信息")
@Data
@TableName("sys_light_record")
public class SysLightRecord extends Model<SysLightRecord> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 记录时间
     */
	@ApiModelProperty("记录时间(不用填写)")
	@TableField("record_date")
	private Date recordDate;
    /**
     * 记录状态
     */
	@ApiModelProperty("记录状态(添加时候必填，更新不用)")
	@TableField("record_status")
	private String recordStatus;
    /**
     * 记录操作
     */
	@ApiModelProperty("谁进行了操作(添加时候必填，更新不用)")
	@TableField("record_operation")
	private String recordOperation;
    /**
     * 谁进行了操作
     */
	@ApiModelProperty("谁进行了操作(添加时候必填，更新不用)")
	@TableField("record_user_id")
	private Long recordUserId;

    /**
     * 对应sys_light_info里的id
     */
	@ApiModelProperty("对应sys_light_info里的id(添加时候必填，更新不用)")
	@TableField("light_info_id")
	private String lightInfoId;

	/**
	 * 是否可见
	 */
	@ApiModelProperty("用来标记删除的不需要添加，默认为1")
	@TableField("exist")
	private int exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
