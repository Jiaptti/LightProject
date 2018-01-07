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
@TableName("sys_street")
public class SysStreet extends Model<SysStreet> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 街道名称
     */
	@TableField("street_name")
	private String streetName;
    /**
     * 街道所属id
     */
	@TableField("street_id")
	private Integer streetId;

	@TableField(exist = false)
	private Long areaId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
