package com.viroyal.light.module.light.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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
@TableName("sys_light")
public class SysLight extends Model<SysLight> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
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
	@TableField("traffic_flow")
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
	 * 数据提交的时间
	 */
	@ApiModelProperty("数据提交的时间(不用填)")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date datetime;
    /**
     * 对应sys_light_info的id
     */
	@ApiModelProperty("对应sys_light_info的id(添加时候必填，更新选填)")
    @TableField("info_id")
	private Long infoId;
    /**
     * 亮度
     */
	@ApiModelProperty("亮度(添加时候必填，更新选填)")
	private Integer lightness;

	@ApiModelProperty("最后一次修改时间(不用填)")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField("last_update_time")
	private Date lastUpdateTime;

	@ApiModelProperty("查询出来的数据，添加和修改的时候，都不用管")
	@TableField(exist = false)
	private String code;

	@ApiModelProperty("查询出来的数据，添加和修改的时候，都不用管")
	@TableField(exist = false)
	private String lightInfo;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
