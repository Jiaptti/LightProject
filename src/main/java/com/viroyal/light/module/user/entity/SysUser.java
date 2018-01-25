package com.viroyal.light.module.user.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */

@Data
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户昵称
     */
	private String nickname;

	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 手机号码
	 */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 密码
     */
	private String pswd;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
    /**
     * 最后登录时间
     */
	@TableField("last_login_time")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
	private String status;

	@TableField("create_name_id")
	private String createNameId;

	@TableField("last_update_time")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;

	@TableField("last_update_name_id")
	private String lastUpdateNameId;

	/**
	 * 角色ID
	 */
	@TableField(exist = false)
	private Long roleId;

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
