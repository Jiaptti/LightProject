package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 路灯记录实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
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
	private String code;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRDate() {
		return rDate;
	}

	public void setRDate(Date rDate) {
		this.rDate = rDate;
	}

	public String getRStatus() {
		return rStatus;
	}

	public void setRStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getROperation() {
		return rOperation;
	}

	public void setROperation(String rOperation) {
		this.rOperation = rOperation;
	}

	public Long getRUserid() {
		return rUserid;
	}

	public void setRUserid(Long rUserid) {
		this.rUserid = rUserid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
