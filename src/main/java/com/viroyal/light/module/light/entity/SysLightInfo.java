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
 * 路灯信息实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@TableName("sys_light_info")
public class SysLightInfo extends Model<SysLightInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 路灯编码
     */
	private String code;
    /**
     * 路灯信息
     */
	private String info;
    /**
     * 1:正在使用，0:刚注册信息并没有投入使用
     */
	private String status;
    /**
     * 经度
     */
	private Float longitude;
    /**
     * 纬度
     */
	private Float latitude;
	/**
	 * 引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间
	 */
	@TableField("strategy_id")
	private String strategyId;
    /**
     * 所属街道id
     */
	@TableField("street_id")
	private Long streetId;
	/**
	 * 所属集合id
	 */
	@TableField("group_id")
	private Long groupId;
    /**
     * 电压报警阀值
     */
	@TableField("voltage_threshold")
	private Integer voltageThreshold;
    /**
     * 电流报警阀值
     */
	@TableField("current_threshold")
	private Integer currentThreshold;
    /**
     * 温度报警阀值
     */
	@TableField("temperature_threshold")
	private Integer temperatureThreshold;
    /**
     * 湿度报警阀值
     */
	@TableField("humidity_threshold")
	private Integer humidityThreshold;
    /**
     * 亮度报警阀值
     */
	@TableField("lightness_threshold")
	private Integer lightnessThreshold;
    /**
     * 电压过载阀值
     */
	@TableField("voltage_overload")
	private Integer voltageOverload;
    /**
     * 电流过载阀值
     */
	@TableField("current_overload")
	private Integer currentOverload;
    /**
     * 温度过载阀值
     */
	@TableField("temperature_overload")
	private Integer temperatureOverload;
    /**
     * 师傅过载阀值
     */
	@TableField("humidity_overload")
	private Integer humidityOverload;
    /**
     * 亮度过载阀值
     */
	@TableField("lightness_overload")
	private Integer lightnessOverload;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
