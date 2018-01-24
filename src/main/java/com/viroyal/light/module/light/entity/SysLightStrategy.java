package com.viroyal.light.module.light.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 路灯决策实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */

@Data
@TableName("sys_light_strategy")
public class SysLightStrategy extends Model<SysLightStrategy> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * 车流量少时的亮度
     */
	@TableField("smooth_level")
	private Integer smoothLevel;

    /**
     * 车流量多时的亮度
     */
	@TableField("traffic_level")
	private Integer trafficLevel;

    /**
     * 打开时间
     */
	@TableField("open_time")
	private String openTime;

    /**
     * 关闭时间
     */
	@TableField("close_time")
	private String closeTime;

    /**
     * 冬季，夏季
     */
	private String type;

	/**
	 * 策略开始时间
	 */
	@TableField("strategy_open_time")
	@JSONField(format="yyyy-MM-dd")
	private Date strategyOpenTime;

	/**
	 * 策略结束时间
	 */
	@TableField("strategy_close_time")
	@JSONField(format="yyyy-MM-dd")
	private Date strategyCloseTime;

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
