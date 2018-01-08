package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户路灯关系实体类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Data
@TableName("sys_user_light")
public class SysUserLight extends Model<SysUserLight> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 路灯ID
     */
	@TableField("light_id")
	private Long lightId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
