package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
     * 所属街道id
     */
	@TableField("street_id")
	private Long streetId;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Long getStreetId() {
		return streetId;
	}

	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

	public Integer getVoltageThreshold() {
		return voltageThreshold;
	}

	public void setVoltageThreshold(Integer voltageThreshold) {
		this.voltageThreshold = voltageThreshold;
	}

	public Integer getCurrentThreshold() {
		return currentThreshold;
	}

	public void setCurrentThreshold(Integer currentThreshold) {
		this.currentThreshold = currentThreshold;
	}

	public Integer getTemperatureThreshold() {
		return temperatureThreshold;
	}

	public void setTemperatureThreshold(Integer temperatureThreshold) {
		this.temperatureThreshold = temperatureThreshold;
	}

	public Integer getHumidityThreshold() {
		return humidityThreshold;
	}

	public void setHumidityThreshold(Integer humidityThreshold) {
		this.humidityThreshold = humidityThreshold;
	}

	public Integer getLightnessThreshold() {
		return lightnessThreshold;
	}

	public void setLightnessThreshold(Integer lightnessThreshold) {
		this.lightnessThreshold = lightnessThreshold;
	}

	public Integer getVoltageOverload() {
		return voltageOverload;
	}

	public void setVoltageOverload(Integer voltageOverload) {
		this.voltageOverload = voltageOverload;
	}

	public Integer getCurrentOverload() {
		return currentOverload;
	}

	public void setCurrentOverload(Integer currentOverload) {
		this.currentOverload = currentOverload;
	}

	public Integer getTemperatureOverload() {
		return temperatureOverload;
	}

	public void setTemperatureOverload(Integer temperatureOverload) {
		this.temperatureOverload = temperatureOverload;
	}

	public Integer getHumidityOverload() {
		return humidityOverload;
	}

	public void setHumidityOverload(Integer humidityOverload) {
		this.humidityOverload = humidityOverload;
	}

	public Integer getLightnessOverload() {
		return lightnessOverload;
	}

	public void setLightnessOverload(Integer lightnessOverload) {
		this.lightnessOverload = lightnessOverload;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
