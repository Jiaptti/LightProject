package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.viroyal.light.module.location.entity.SysRegion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="路灯信息")
@TableName("sys_light_info")
public class SysLightInfo extends Model<SysLightInfo> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 路灯编码
     */
	@ApiModelProperty("路灯编码(添加时候必填，更新选填)")
	@TableField("code")
	private String code;
    /**
     * 路灯信息
     */
	@ApiModelProperty("路灯信息(添加时候必填，更新选填)")
	@TableField("light_info")
	private String lightInfo;
    /**
     * 1:正在使用，0:刚注册信息并没有投入使用
     */
	@ApiModelProperty("是否启用( 1:正在使用，0:刚注册信息并没有投入使用)(添加时候必填，更新选填)")
	@TableField("status")
	private String status;
    /**
     * 经度
     */
	@ApiModelProperty("经度(选填)")
	@TableField("longitude")
	private Float longitude;
    /**
     * 纬度
     */
	@ApiModelProperty("纬度(选填)")
	@TableField("latitude")
	private Float latitude;
	/**
	 * 引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间
	 */
	@ApiModelProperty("策略id(引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间),详细看sys_strategy表(添加时候必填，更新选填)")
	@TableField("strategy_id")
	private String strategyId;
	/**
	 * 所属用户id
	 */
	@ApiModelProperty("所属维修员id(即用户id,看数据库谁是维修员)(添加，更新都选填)")
	@TableField("user_id")
	private Long userId;
    /**
     * 所属街道id
     */
	@ApiModelProperty("所属街道id(即sys_region表中的desc为街道的common_region_id)(添加时候必填，更新选填)")
	@TableField("street_id")
	private Long streetId;
	/**
	 * 所属组的id
	 */
	@ApiModelProperty("所属组的id(即sys_group表中的id)(选填)")
	@TableField("group_id")
	private Long groupId;
	/**
	 * 路灯自动上报周期
	 */
	@ApiModelProperty("路灯自动上报周期(时间为毫秒，多久提交一次数据到sys_light表，默认60秒)")
	@TableField("auto_report")
	private Long autoReport;
    /**
     * 电压报警阀值
     */
	@ApiModelProperty("电压报警阀值(选填,后期可能要填写)")
	@TableField("voltage_threshold")
	private Integer voltageThreshold;
    /**
     * 电流报警阀值
     */
	@ApiModelProperty("电流报警阀值(选填,后期可能要填写)")
	@TableField("current_threshold")
	private Integer currentThreshold;
    /**
     * 温度报警阀值
     */
	@ApiModelProperty("温度报警阀值(选填,后期可能要填写)")
	@TableField("temperature_threshold")
	private Integer temperatureThreshold;
    /**
     * 湿度报警阀值
     */
	@ApiModelProperty("湿度报警阀值(选填,后期可能要填写)")
	@TableField("humidity_threshold")
	private Integer humidityThreshold;
    /**
     * 亮度报警阀值
     */
	@ApiModelProperty("亮度报警阀值(选填,后期可能要填写)")
	@TableField("lightness_threshold")
	private Integer lightnessThreshold;
    /**
     * 电压过载阀值
     */
	@ApiModelProperty("电压过载阀值(选填,后期可能要填写)")
	@TableField("voltage_overload")
	private Integer voltageOverload;
    /**
     * 电流过载阀值
     */
	@ApiModelProperty("电流过载阀值(选填,后期可能要填写)")
	@TableField("current_overload")
	private Integer currentOverload;
    /**
     * 温度过载阀值
     */
	@ApiModelProperty("温度过载阀值(选填,后期可能要填写)")
	@TableField("temperature_overload")
	private Integer temperatureOverload;
    /**
     * 湿度过载阀值
     */
	@ApiModelProperty("湿度过载阀值(选填,后期可能要填写)")
	@TableField("humidity_overload")
	private Integer humidityOverload;
    /**
     * 亮度过载阀值
     */
	@ApiModelProperty("亮度过载阀值(选填,后期可能要填写)")
	@TableField("lightness_overload")
	private Integer lightnessOverload;

	/**
	 * 是否可见
	 */
	@ApiModelProperty("用来标记删除的不需要添加，默认为1")
	@TableField("exist")
	private Integer exist;

	@ApiModelProperty("查询出来的数据，添加和修改的时候，都不用管")
	@TableField(exist = false)
	private String openTime;

	@ApiModelProperty("查询出来的数据，添加和修改的时候，都不用管")
	@TableField(exist = false)
	private String closeTime;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
