package com.viroyal.light.module.light.entity.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="路灯灯杆")
public class SysBasicLightPoleVo{
    /**
     * 主键
     */
	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 灯杆品牌
     */
	@ApiModelProperty("灯杆品牌(添加必填，更新选填)")
	private String poleBrand;
    /**
     * 灯杆使用寿命
     */
	@ApiModelProperty("灯杆使用寿命(添加，更新选填)")
	private String poleLife;
    /**
     * 灯杆额定运行电压
     */
	@ApiModelProperty("灯杆额定运行电压(添加，更新选填)")
	private String poleOperatingVoltage;
    /**
     * 灯杆额定频率
     */
	@ApiModelProperty("灯杆额定频率(添加，更新选填)")
	private String poleFrequency;
    /**
     * 灯杆工作环境温度
     */
	@ApiModelProperty("灯杆工作环境温度(添加，更新选填)")
	private String poleTemperature;
    /**
     * 灯杆型号
     */
	@ApiModelProperty("灯杆型号(添加，更新选填)")
	private String poleType;
    /**
     * 灯杆其他参数说明
     */
	@ApiModelProperty("灯杆其他参数说明(添加，更新选填)")
	private String poleParamDesc;
}
