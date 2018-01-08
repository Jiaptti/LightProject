package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色实体类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Data
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 角色名称
     */
	private String name;
    /**
     * 角色类型
     */
	private String type;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
