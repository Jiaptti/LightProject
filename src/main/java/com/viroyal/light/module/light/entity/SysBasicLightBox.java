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
 * 灯箱基础信息表
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-31
 */
@Data
@TableName("sys_basic_light_box")
public class SysBasicLightBox extends Model<SysBasicLightBox> {

    private static final long serialVersionUID = 1L;

    /**
     * 灯箱主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 灯箱品牌
     */
	@TableField("box_brand")
	private String boxBrand;
    /**
     * 灯箱型号
     */
	@TableField("box_type")
	private String boxType;
    /**
     * 灯箱规格
     */
	@TableField("box_specification")
	private String boxSpecification;
    /**
     * 灯箱工作电压
     */
	@TableField("box_work_voltage")
	private String boxWorkVoltage;
	/**
	 * 灯箱工作电流
	 */
	@TableField("box_work_current")
	private String boxWorkCurrent;
    /**
     * 工作温度
     */
	@TableField("box_work_temperature")
	private String boxWorkTemperature;
    /**
     * 灯箱其他参数说明
     */
	@TableField("box_param_desc")
	private String boxParamDesc;
    /**
     * 是否删除
     */
	private Integer exist;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
