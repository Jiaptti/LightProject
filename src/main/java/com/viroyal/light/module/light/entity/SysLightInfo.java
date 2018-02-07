package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 路灯信息实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@TableName("sys_light_info")
public class SysLightInfo extends Model<SysLightInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 路灯编码
     */
	@TableField("code")
	private String code;
    /**
     * 路灯信息
     */
	@TableField("light_info")
	private String lightInfo;
    /**
     * 1:正在使用，0:刚注册信息并没有投入使用
     */
	@TableField("status")
	private String status;
    /**
     * 经度
     */
	@TableField("longitude")
	private Double longitude;
    /**
     * 纬度
     */
	@TableField("latitude")
	private Double latitude;
	/**
	 * 引用策略表的id，根据四季的不同，车流量的不同，来指派亮度以及打开时间
	 */
	@TableField("strategy_id")
	private Long strategyId;
	/**
	 * 所属用户id
	 */
	@TableField("user_id")
	private Long userId;
    /**
     * 所属街道id
     */
	@TableField("street_id")
	private Long streetId;
	/**
	 * 所属组的id
	 */
	@TableField("group_id")
	private Long groupId;
	/**
	 * 路灯自动上报周期
	 */
	@TableField("auto_report")
	private Long autoReport;
	/**
	 * 灯杆id
	 */
	@TableField("pole_id")
	private Long poleId;
	/**
	 * 灯箱id
	 */
	@TableField("box_id")
	private Long boxId;
	/**
	 * 警报id
	 */
	@TableField("alarm_id")
	private Long alarmId;
	/**
	 * 是否可见
	 */
	@TableField("exist")
	private Integer exist;
	/**
	 * 安装路灯列表
	 */
	@TableField(exist = false)
	List<SysBasicLight> basicLightList;
	/**
	 * 分组名
	 */
	@TableField(exist = false)
	String groupName;
	/**
	 * 策略名
	 */
	@TableField(exist = false)
	String strategyName;
	/**
	 * 负责人名字
	 */
	@TableField(exist = false)
	String responsibleName;
	/**
	 * 负责人账号
	 */
	@TableField(exist = false)
	String responsibleAccount;
	/**
	 * 所属街道
	 */
	@TableField(exist = false)
	String streetName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
