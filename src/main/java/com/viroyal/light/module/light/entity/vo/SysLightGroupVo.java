package com.viroyal.light.module.light.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 路灯组 实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@ApiModel(value="路灯分组对象")
public class SysLightGroupVo {


	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	private Long id;
    /**
     * 组id
     */
	@ApiModelProperty("组id(用数字命名，添加的时候必填，修改不用)")
	private Integer groupId;

    /**
     * 组名
     */
	@ApiModelProperty("组名 eg:xx路的路灯组，xx区的路灯组，命名随意(添加的时候必填，修改不用)")
	private String groupName;
    /**
     * 谁负责的组
     */
	@ApiModelProperty("组由谁负责，填的是负责人的用户id(不用填，更新的时候带上)")
	private Long responsibleId;

}
