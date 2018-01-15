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

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 记录时间
     */
	@TableField("r_date")
	private Date rDate;
    /**
     * 记录状态
     */
	@TableField("r_status")
	private String rStatus;
    /**
     * 记录操作
     */
	@TableField("r_operation")
	private String rOperation;
    /**
     * 谁进行了操作
     */
	@TableField("r_userid")
	private Long rUserid;

    /**
     * 对应sys_light_info里的code
     */
	@ApiModelProperty("对应sys_light_info里的code(添加时候必填，更新不用)")
	private String code;

	/**
	 * 是否可见
	 */
	@ApiModelProperty("用来标记删除的不需要添加，默认为1")
	@TableField("flag")
	private int flag;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
