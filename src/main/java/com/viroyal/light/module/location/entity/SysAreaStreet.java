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
 * 区域街道关联实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@TableName("sys_area_street")
public class SysAreaStreet extends Model<SysAreaStreet> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 区域id
     */
	@TableField("area_id")
	private Long areaId;
    /**
     * 街道id
     */
	@TableField("street_id")
	private Long streetId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
