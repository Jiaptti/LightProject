package com.viroyal.light.module.entity.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

	@TableId(type= IdType.AUTO)
	private int id;
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
