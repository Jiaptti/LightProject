package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * <p>
 *  用户角色实现类
 * </p>
 * @author jiaptti
 * @since 2017-12-01
 */
@Data
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 用户ID
	 */
	private Long uid;
	/**
	 * 角色ID
	 */
	private Long rid;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
