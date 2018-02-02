package com.viroyal.light.module.light.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 路灯和安装路灯关联表
 * </p>
 *
 * @author jiaptti
 * @since 2018-02-02
 */
@Data
@ApiModel(value="路灯和安装路灯关联对象")
public class SysInfoBasicLightVo {

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 路灯信息id
     */
	@ApiModelProperty("路灯信息id(添加必填，更新选填)")
	private Long infoId;
    /**
     * 安装路灯id
     */
	@ApiModelProperty("安装路灯id，多个用逗号隔开(添加必填，更新选填)")
	private Long basicId;


}
