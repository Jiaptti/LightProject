package com.viroyal.light.module.light.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 报警基准表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@ApiModel(value="路灯警报阈值")
public class SysBasicAlarmVo {
    /**
     * 路灯警报主键
     */
	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 路灯报警阈值上报时间间隔
     */
	@ApiModelProperty("路灯报警阈值上报时间间隔(添加必填，更新选填)")
	private Long alarmReportTime;
    /**
     * 最大电压报警阈值
     */
	@ApiModelProperty("最大电压报警阈值(添加必填，更新选填)")
	private Integer alarmMaxVoltage;
    /**
     * 最小电压报警阈值
     */
	@ApiModelProperty("最小电压报警阈值(添加必填，更新选填)")
	private Integer alarmMinVoltage;
    /**
     * 最大温度报警阈值
     */
	@ApiModelProperty("最大温度报警阈值(添加必填，更新选填)")
	private Integer alarmMaxTemperature;
    /**
     * 最小温度报警阈值
     */
	@ApiModelProperty("最小温度报警阈值(添加必填，更新选填)")
	private Integer alarmMinTemperature;
    /**
     * 最大电流报警阈值
     */
	@ApiModelProperty("最大电流报警阈值(添加必填，更新选填)")
	private Integer alarmMaxCurrent;
    /**
     * 最小电流报警阈值
     */
	@ApiModelProperty("最小电流报警阈值(添加必填，更新选填)")
	private Integer alarmMinCurrent;
    /**
     * 最大湿度报警阈值
     */
	@ApiModelProperty("最大湿度报警阈值(添加必填，更新选填)")
	private Integer alarmMaxHumidity;
    /**
     * 最小湿度报警阈值
     */
	@ApiModelProperty("最小湿度报警阈值(添加必填，更新选填)")
	private Integer alarmMinHumidity;
    /**
     * 最大亮度报警阈值
     */
	@ApiModelProperty("最大亮度报警阈值(添加必填，更新选填)")
	private Integer alarmMaxBrightness;
    /**
     * 最小亮度报警阈值
     */
	@ApiModelProperty("最小亮度报警阈值(添加必填，更新选填)")
	private Integer alarmMinBrightness;
    /**
     * 车流量报警阈值
     */
	@ApiModelProperty("车流量报警阈值(添加必填，更新选填)")
	private Integer alarmTraffic;
    /**
     * 报警阈值名称
     */
	@ApiModelProperty("报警阈值名称(添加必填，更新选填)")
	private String alarmName;
}
