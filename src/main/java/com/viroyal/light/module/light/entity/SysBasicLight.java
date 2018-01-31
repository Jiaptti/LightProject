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
 * 路灯基础信息表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@TableName("sys_basic_light")
public class SysBasicLight extends Model<SysBasicLight> {

    private static final long serialVersionUID = 1L;

    /**
     * 安装路灯主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 安装路灯品牌
     */
	@TableField("light_brand")
	private String lightBrand;
    /**
     * 安装路灯功率(W)
     */
	@TableField("light_power")
	private Integer lightPower;
    /**
     * 安装路灯最高工作温度
     */
	@TableField("light_max_temperature")
	private Integer lightMaxTemperature;
    /**
     * 安装路灯最低工作温度
     */
	@TableField("light_min_temperature")
	private Integer lightMinTemperature;
    /**
     * 安装路灯最高输入电压
     */
	@TableField("light_max_voltage")
	private Integer lightMaxVoltage;
    /**
     * 安装路灯最低输入电压
     */
	@TableField("light_min_voltage")
	private Integer lightMinVoltage;
    /**
     * 最大湿度
     */
	@TableField("light_max_humidity")
	private Integer lightMaxHumidity;
    /**
     * 安装路灯的最低湿度
     */
	@TableField("light_min_humidity")
	private Integer lightMinHumidity;
    /**
     * 安装路灯寿命(h)
     */
	@TableField("light_life")
	private Integer lightLife;
    /**
     * 安装路灯最大工作电流
     */
	@TableField("light_max_current")
	private Integer lightMaxCurrent;
    /**
     * 安装路灯最小工作电流
     */
	@TableField("light_min_current")
	private Integer lightMinCurrent;
    /**
     * 安装路灯型号
     */
	@TableField("light_type")
	private String lightType;
    /**
     * 安装路灯最大色温
     */
	@TableField("light_color_max_temperature")
	private Integer lightColorMaxTemperature;
    /**
     * 安装路灯最小色温
     */
	@TableField("light_color_min_temperature")
	private Integer lightColorMinTemperature;
    /**
     * 安装路灯其他参数说明
     */
	@TableField("light_param_desc")
	private String lightParamDesc;
    /**
     * 是否删除
     */
	private Integer exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
