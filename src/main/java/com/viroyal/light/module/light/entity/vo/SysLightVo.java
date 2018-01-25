package com.viroyal.light.module.light.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 路灯实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@ApiModel(value="路灯数据信息")
public class SysLightVo{
	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 1:开灯，0:关灯
     */
	@ApiModelProperty("状态(是否打开)(添加时候必填，更新选填)")
	private Integer status;
    /**
     * 电压
     */
	@ApiModelProperty("电压(添加时候必填，更新选填)")
	private Integer voltage;
    /**
     * 电流
     */
	@ApiModelProperty("电流(添加时候必填，更新选填)")
	private Integer current;
    /**
     * 车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息
     */
	@ApiModelProperty("车流量(1表示车流量多，0表示车流量少)(添加时候必填，更新选填)")
	private Integer trafficFlow;
    /**
     * 温度
     */
	@ApiModelProperty("温度(添加时候必填，更新选填)")
	private Integer temperature;
    /**
     * 湿度
     */
	@ApiModelProperty("湿度(添加时候必填，更新选填)")
	private Integer humidity;
    /**
     * 对应sys_light_info的id
     */
	@ApiModelProperty("对应sys_light_info的id(添加时候必填，更新选填)")
	private Long infoId;
    /**
     * 亮度
     */
	@ApiModelProperty("亮度(添加时候必填，更新选填)")
	private Integer lightness;
}
