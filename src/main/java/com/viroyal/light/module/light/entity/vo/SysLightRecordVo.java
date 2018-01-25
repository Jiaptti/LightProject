package com.viroyal.light.module.light.entity.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 路灯记录实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@ApiModel(value="地区信息")
@Data
public class SysLightRecordVo {

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 记录状态
     */
	@ApiModelProperty("记录状态(添加时候必填，更新不用)")
	private String recordStatus;
    /**
     * 记录操作
     */
	@ApiModelProperty("谁进行了操作(添加时候必填，更新不用)")
	private String recordOperation;

    /**
     * 对应sys_light_info里的id
     */
	@ApiModelProperty("对应sys_light_info里的id(添加时候必填，更新不用)")
	private String lightInfoId;

}
