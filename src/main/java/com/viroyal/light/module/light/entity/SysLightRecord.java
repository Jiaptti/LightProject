package com.viroyal.light.module.light.entity;

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
 * 
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-24
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


	public Long getId() {
		return id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
