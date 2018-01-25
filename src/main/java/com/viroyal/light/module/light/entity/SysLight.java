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
@TableName("sys_light")
public class SysLight extends Model<SysLight> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 1:开灯，0:关灯
     */
	private Integer status;
    /**
     * 电压
     */
	private Integer voltage;
    /**
     * 电流
     */
	private Integer current;
    /**
     * 车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息
     */
	@TableField("traffic_flow")
	private Integer trafficFlow;
    /**
     * 温度
     */
	private Integer temperature;
    /**
     * 湿度
     */
	private Integer humidity;
	/**
	 * 数据提交的时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date datetime;
    /**
     * 对应sys_light_info的id
     */
    @TableField("info_id")
	private Long infoId;
    /**
     * 亮度
     */
	private Integer lightness;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField("last_update_time")
	private Date lastUpdateTime;

	@TableField(exist = false)
	private String code;

	@TableField(exist = false)
	private String lightInfo;
	/**
	 * 是否可见
	 */
	@TableField("exist")
	private Integer exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
