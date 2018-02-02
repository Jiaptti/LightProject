package com.viroyal.light.module.light.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 路灯基础信息表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@ApiModel(value="安装路灯")
public class SysBasicLightVo{
    /**
     * 安装路灯主键
     */
	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 安装路灯品牌
     */
	@ApiModelProperty("安装路灯品牌(添加必填，更新选填)")
	private String lightBrand;
    /**
     * 安装路灯功率(W)
     */
	@ApiModelProperty("安装路灯功率(W)(添加必填，更新选填)")
	private Integer lightPower;
	/**
	 * 安装路灯型号
	 */
	@ApiModelProperty("安装路灯型号(添加必填，更新选填)")
	private String lightType;
    /**
     * 安装路灯最高工作温度
     */
	@ApiModelProperty("安装路灯最高工作温度(添加，更新选填)")
	private Integer lightMaxTemperature;
    /**
     * 安装路灯最低工作温度
     */
	@ApiModelProperty("安装路灯最低工作温度(添加，更新选填)")
	private Integer lightMinTemperature;
    /**
     * 安装路灯最高输入电压
     */
	@ApiModelProperty("安装路灯最高输入电压(添加，更新选填)")
	private Integer lightMaxVoltage;
    /**
     * 安装路灯最低输入电压
     */
	@ApiModelProperty("安装路灯最低输入电压(添加，更新选填)")
	private Integer lightMinVoltage;
    /**
     * 安装路灯最大湿度
     */
	@ApiModelProperty("安装路灯最大湿度(添加，更新选填)")
	private Integer lightMaxHumidity;
    /**
     * 安装路灯的最低湿度
     */
	@ApiModelProperty("安装路灯的最低湿度(添加，更新选填)")
	private Integer lightMinHumidity;
    /**
     * 安装路灯寿命(h)
     */
	@ApiModelProperty("安装路灯寿命(h)(添加，更新选填)")
	private Integer lightLife;
    /**
     * 安装路灯最大工作电流
     */
	@ApiModelProperty("安装路灯最大工作电流(添加，更新选填)")
	private Integer lightMaxCurrent;
    /**
     * 安装路灯最小工作电流
     */
	@ApiModelProperty("安装路灯最小工作电流(添加，更新选填)")
	private Integer lightMinCurrent;
    /**
     * 安装路灯最大色温
     */
	@ApiModelProperty("安装路灯最大色温(添加，更新选填)")
	private Integer lightColorMaxTemperature;
    /**
     * 安装路灯最小色温
     */
	@ApiModelProperty("安装路灯最小色温(添加，更新选填)")
	private Integer lightColorMinTemperature;
    /**
     * 安装路灯其他参数说明
     */
	@ApiModelProperty("安装路灯其他参数说明(添加，更新选填)")
	private String lightParamDesc;
}
