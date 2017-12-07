package com.viroyal.light.module.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
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
public class SysPermissionInit extends Model<SysPermissionInit> {

    private static final long serialVersionUID = 1L;

	private String id;
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermissionInit() {
		return permissionInit;
	}

	public void setPermissionInit(String permissionInit) {
		this.permissionInit = permissionInit;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
