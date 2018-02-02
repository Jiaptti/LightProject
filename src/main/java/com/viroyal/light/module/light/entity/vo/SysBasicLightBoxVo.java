package com.viroyal.light.module.light.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 灯箱基础信息表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@ApiModel(value="路灯灯箱")
public class SysBasicLightBoxVo {

    /**
     * 灯箱主键
     */
	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 灯箱品牌
     */
	@ApiModelProperty("灯箱品牌(添加必填，更新选填)")
	private String boxBrand;
    /**
     * 灯箱型号
     */
	@ApiModelProperty("灯箱型号(添加必填，更新选填)")
	private String boxType;
    /**
     * 灯箱规格
     */
	@ApiModelProperty("灯箱规格(添加必填，更新选填)")
	private String boxSpecification;
    /**
     * 灯箱工作电压
     */
	@ApiModelProperty("灯箱工作电压(添加，更新选填)")
	private String boxWorkVoltage;
	/**
	 * 灯箱工作电流
	 */
	@ApiModelProperty("灯箱工作电流(添加，更新选填)")
	private String boxWorkCurrent;
    /**
     * 工作温度
     */
	@ApiModelProperty("工作温度(添加，更新选填)")
	private String boxWorkTemperature;
    /**
     * 灯箱其他参数说明
     */
	@ApiModelProperty("灯箱其他参数说明(添加，更新选填)")
	private String boxParamDesc;
}
