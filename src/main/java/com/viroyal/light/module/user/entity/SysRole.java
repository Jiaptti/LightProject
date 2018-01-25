package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

	/**
	 * 是否可见
	 */
	@TableField("exist")
	private Integer exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
