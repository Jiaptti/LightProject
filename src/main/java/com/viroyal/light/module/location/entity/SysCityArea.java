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
@TableName("sys_city_area")
public class SysCityArea extends Model<SysCityArea> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 对应sys_city中的city_id
     */
	@TableField("city_id")
	private Long cityId;
    /**
     * 对应sys_area中的area_id
     */
	@TableField("area_id")
	private Long areaId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
