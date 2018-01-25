package com.viroyal.light.module.location.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="地区信息")
@Data
public class SysRegionVo {

    @ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
    private Long id;

    /**
     * 地区编号
     */
    @ApiModelProperty("地区id,参考数据库规律添加(添加时候必填，更新不用)")
    private String commonRegionId;

    /**
     * 地区名
     */
    @ApiModelProperty("地区名,参考数据库规律添加(添加时候必填，更新不用)")
    private String regionName;
    /**
     * 地区上级编号
     */
    @ApiModelProperty("地区上级id,参考数据库规律添加(添加时候必填，更新不用)")
    private String upRegionId;
    /**
     * 地区描述
     */
    @ApiModelProperty("地区描述(参考数据库,如地级(直辖)市/市辖区/街道,必填)")
    private String regionDesc;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编(添加时候必填，更新不用)")
    private String postalcode;
}
