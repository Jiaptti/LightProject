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
 * 灯杆基础信息表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@TableName("sys_basic_light_pole")
public class SysBasicLightPole extends Model<SysBasicLightPole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 灯杆品牌
     */
	@TableField("pole_brand")
	private String poleBrand;
    /**
     * 灯杆使用寿命
     */
	@TableField("pole_life")
	private String poleLife;
    /**
     * 灯杆额定运行电压
     */
	@TableField("pole_operating_voltage")
	private String poleOperatingVoltage;
    /**
     * 灯杆额定频率
     */
	@TableField("pole_frequency")
	private String poleFrequency;
    /**
     * 灯杆工作环境温度
     */
	@TableField("pole_temperature")
	private String poleTemperature;
    /**
     * 灯杆型号
     */
	@TableField("pole_type")
	private String poleType;
    /**
     * 灯杆其他参数说明
     */
	@TableField("pole_param_desc")
	private String poleParamDesc;
    /**
     * 是否删除
     */
	private Integer exist;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
