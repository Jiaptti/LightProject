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
@TableName("sys_city")
public class SysCity extends Model<SysCity> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("city_name")
	private String cityName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
