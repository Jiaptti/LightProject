package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_role_permission")
@Data
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 角色ID
	 */
	private Long rid;
	/**
	 * 权限ID
	 */
	private Long pid;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
