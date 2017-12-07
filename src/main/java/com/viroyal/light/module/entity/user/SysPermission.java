package com.viroyal.light.module.entity.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@TableName("sys_permission")
@Data
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * url地址
     */
	private String url;
    /**
     * url描述
     */
	private String name;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
