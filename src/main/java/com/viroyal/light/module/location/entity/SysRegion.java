package com.viroyal.light.module.location.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-09
 */
@Data
@ApiModel(value="地区信息")
@TableName("sys_region")
public class SysRegion extends Model<SysRegion> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * 地区编号
     */
	@ApiModelProperty("地区id,参考数据库规律添加(添加时候必填，更新不用)")
	@TableField("common_region_id")
	private String commonRegionId;

    /**
     * 地区名
     */
	@ApiModelProperty("地区名,参考数据库规律添加(添加时候必填，更新不用)")
	@TableField("region_name")
	private String regionName;
    /**
     * 地区上级编号
     */
	@ApiModelProperty("地区上级id,参考数据库规律添加(添加时候必填，更新不用)")
	@TableField("up_region_id")
	private String upRegionId;
    /**
     * 地区描述
     */
	@ApiModelProperty("地区描述(参考数据库,如地级(直辖)市/市辖区/街道,必填)")
	@TableField("region_desc")
	private String regionDesc;
    /**
     * 邮编
     */
	@ApiModelProperty("邮编(添加时候必填，更新不用)")
	private String postalcode;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
