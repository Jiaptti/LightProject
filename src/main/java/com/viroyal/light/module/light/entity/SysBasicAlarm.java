package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 报警基准表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@TableName("sys_basic_alarm")
public class SysBasicAlarm extends Model<SysBasicAlarm> {

    private static final long serialVersionUID = 1L;

    /**
     * 警报主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 报警阈值上报时间间隔
     */
	@TableField("alarm_report_time")
	private Long alarmReportTime;
    /**
     * 最大电压报警阈值
     */
	@TableField("alarm_max_voltage")
	private Integer alarmMaxVoltage;
    /**
     * 最小电压报警阈值
     */
	@TableField("alarm_min_voltage")
	private Integer alarmMinVoltage;
    /**
     * 最大温度报警阈值
     */
	@TableField("alarm_max_temperature")
	private Integer alarmMaxTemperature;
    /**
     * 最小温度报警阈值
     */
	@TableField("alarm_min_temperature")
	private Integer alarmMinTemperature;
    /**
     * 最大电流报警阈值
     */
	@TableField("alarm_max_current")
	private Integer alarmMaxCurrent;
    /**
     * 最小电流报警阈值
     */
	@TableField("alarm_min_current")
	private Integer alarmMinCurrent;
    /**
     * 最大湿度报警阈值
     */
	@TableField("alarm_max_humidity")
	private Integer alarmMaxHumidity;
    /**
     * 最小湿度报警阈值
     */
	@TableField("alarm_min_humidity")
	private Integer alarmMinHumidity;
    /**
     * 最大亮度报警阈值
     */
	@TableField("alarm_max_brightness")
	private Integer alarmMaxBrightness;
    /**
     * 最小亮度报警阈值
     */
	@TableField("alarm_min_brightness")
	private Integer alarmMinBrightness;
    /**
     * 车流量报警阈值
     */
	@TableField("alarm_traffic")
	private Integer alarmTraffic;
    /**
     * 报警阈值名称
     */
	@TableField("alarm_name")
	private String alarmName;
    /**
     * 是否删除
     */
	private Integer exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
