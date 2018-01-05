package com.viroyal.light.module.location.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-01-04
 */
@Data
@TableName("sys_area")
public class SysArea extends Model<SysArea> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 区域名称
     */
	@TableField("area_name")
	private String areaName;
    /**
     * 区域所属id
     */
	@TableField("area_id")
	private Integer areaId;

	/**
	 * 城市所属id
	 */
	@TableField(exist = false)
	private Long cityId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
