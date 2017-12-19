package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_permission_init")
@Data
public class SysPermissionInit extends Model<SysPermissionInit> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 链接地址
     */
	private String url;
    /**
     * 需要具备的权限
     */
	@TableField("permission_init")
	private String permissionInit;
    /**
     * 排序
     */
	private Integer sort;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
