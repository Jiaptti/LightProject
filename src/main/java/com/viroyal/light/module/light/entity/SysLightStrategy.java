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
 * 
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
