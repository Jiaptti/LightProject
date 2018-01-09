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
 * @since 2018-01-09
 */
@Data
@TableName("sys_region")
public class SysRegion extends Model<SysRegion> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 地区编号
     */
	@TableField("common_region_id")
	private String commonRegionId;
    /**
     * 地区名
     */
	@TableField("region_name")
	private String regionName;
    /**
     * 地区上级编号
     */
	@TableField("up_region_id")
	private String upRegionId;
    /**
     * 地区描述
     */
	@TableField("region_desc")
	private String regionDesc;
    /**
     * 邮编
     */
	private String postalcode;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
