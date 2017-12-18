package com.viroyal.light.module.entity.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.viroyal.light.module.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class SysPermission extends BaseEntity<SysPermission> {

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
	private String perms;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
