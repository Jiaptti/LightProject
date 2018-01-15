package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 路灯决策实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@ApiModel(value="路灯决策信息")
@Data
@TableName("sys_light_strategy")
public class SysLightStrategy extends Model<SysLightStrategy> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * 车流量少时的亮度
     */
	@ApiModelProperty("车流量少时的亮度(添加时候必填，更新不用)")
	@TableField("smooth_level")
	private Integer smoothLevel;

    /**
     * 车流量多时的亮度
     */
	@ApiModelProperty("车流量多时的亮度(添加时候必填，更新不用)")
	@TableField("traffic_level")
	private Integer trafficLevel;

    /**
     * 打开时间
     */
	@ApiModelProperty("打开时间(添加时候必填，更新不用)")
	@TableField("open_time")
	private String openTime;

    /**
     * 关闭时间
     */
	@ApiModelProperty("关闭时间(添加时候必填，更新不用)")
	@TableField("close_time")
	private String closeTime;

    /**
     * 冬季，夏季
     */
	@ApiModelProperty("季节(冬季，夏季等)(添加时候必填，更新不用)")
	private String type;

	/**
	 * 是否可见
	 */
	@ApiModelProperty("用来标记删除的不需要添加，默认为1")
	@TableField("flag")
	private int flag;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
