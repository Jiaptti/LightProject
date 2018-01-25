package com.viroyal.light.module.location.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="地区信息")
@Data
public class SysStreetVo {

    /**
     * 地区编号
     */
    @ApiModelProperty("地区id,参考数据库规律添加(必填)")
    private String areaId;

    /**
     * 街道名
     */
    @ApiModelProperty("街道名,参考数据库规律添加(必填)")
    private String streetName;

    /**
     * 邮编
     */
    @ApiModelProperty("邮编(选填)")
    private String postalcode;
}
