package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 角色实体类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@ApiModel(value="角色信息")
@Data
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * 角色名称
     */
	@ApiModelProperty("角色名称(添加时候必填，更新不用)")
	@NotNull(message = "{role.name.not.empty}")
	@Size(min=2, max=10, message="{role.name.length}")
	@Pattern(regexp = "[\\u4e00-\\u9fa5]+", message = "{role.name.right.format}")
	private String name;

    /**
     * 角色类型
     */
	@ApiModelProperty("角色类型(添加时候必填，更新不用)")
	@NotNull(message = "{role.type.not.empty}")
	@Size(min=2, max=10, message="{role.type.length}")
	private String type;

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
