package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 权限实体类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@TableName("sys_permission")
@Data
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * url地址
     */
	private String url;
    /**
     * url描述
     */
	private String name;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@TableField("perms")
	private String perms;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
