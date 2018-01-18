package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色权限实体类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@ApiModel(value="角色权限信息")
@Data
@TableName("sys_role_permission")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

	/**
	 * 角色ID
	 */
	@ApiModelProperty("角色ID(添加时候必填，更新不用)")
	private Long rid;

	/**
	 * 权限ID
	 */
	@ApiModelProperty("权限ID(添加时候必填，更新不用)")
	private Long pid;

	/**
	 * 是否可见
	 */
	@ApiModelProperty("用来标记删除的不需要添加，默认为1")
	@TableField("exist")
	private Integer exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
