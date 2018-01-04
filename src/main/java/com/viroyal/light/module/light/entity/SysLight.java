package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
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
    /**
     * 电流
     */
	private Long current;
    /**
     * 车流量,暂时用1表示车流量多，0表示车流量少，具体按照路灯获取的信息
     */
	@TableField("traffic_flow")
	private String trafficFlow;
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
	private Date datetime;
    /**
     * 对应light_info的code
     */
	private String code;
    /**
     * 亮度
     */
	private Integer lightness;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getVoltage() {
		return voltage;
	}

	public void setVoltage(Long voltage) {
		this.voltage = voltage;
	}

	public Long getCurrent() {
		return current;
	}

	public void setCurrent(Long current) {
		this.current = current;
	}

	public String getTrafficFlow() {
		return trafficFlow;
	}

	public void setTrafficFlow(String trafficFlow) {
		this.trafficFlow = trafficFlow;
	}

	public Long getTemperature() {
		return temperature;
	}

	public void setTemperature(Long temperature) {
		this.temperature = temperature;
	}

	public Long getHumidity() {
		return humidity;
	}

	public void setHumidity(Long humidity) {
		this.humidity = humidity;
	}

	public Long getAutoreport() {
		return autoreport;
	}

	public void setAutoreport(Long autoreport) {
		this.autoreport = autoreport;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLightness() {
		return lightness;
	}

	public void setLightness(Integer lightness) {
		this.lightness = lightness;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
