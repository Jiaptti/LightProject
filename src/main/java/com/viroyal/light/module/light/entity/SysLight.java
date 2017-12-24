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
 * @since 2017-12-24
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
	private String status;
    /**
     * 电压
     */
	private Long voltage;
	private Long current;
    /**
     * 车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息
     */
	@TableField("traffic_flow")
	private String trafficFlow;
    /**
     * 省
     */
	private String province;
    /**
     * 市
     */
	private String town;
    /**
     * 区
     */
	private String area;
    /**
     * 街道
     */
	private String street;
    /**
     * 温度
     */
	private Long temperature;
    /**
     * 湿度
     */
	private Long humidity;
    /**
     * 路灯自动上报周期
     */
	private Long autoreport;
    /**
     * 引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间
     */
	private String strategy;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
