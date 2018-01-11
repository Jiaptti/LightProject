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
@ApiModel(value="用户信息")
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户昵称
     */
	@ApiModelProperty("用户昵称(必填)")
	private String nickname;

	/**
	 * 用户姓名
	 */
	@ApiModelProperty("用户姓名(必填)")
	private String username;

	/**
	 * 手机号码
	 */
	@ApiModelProperty("手机号码(必填)")
	private String phone;
    /**
     * 邮箱
     */
	@ApiModelProperty("邮箱(选填)")
	private String email;
    /**
     * 密码
     */
	@ApiModelProperty("密码(必填)，记得加密")
	private String pswd;
    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间(不必添加)")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
    /**
     * 最后登录时间
     */
	@ApiModelProperty("最后登录时间(不必填)")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField("last_login_time")
	private Date lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
	@ApiModelProperty("用户状态(1:有效，0:禁止登录,必填)")
	private String status;

	@ApiModelProperty("创建用户的id(是谁创建了这个用户，添加的时候，看数据库自己的id号,因为是你创建)")
	@TableField("create_name_id")
	private String createNameId;

	@ApiModelProperty("最后修改时间(不必填)")
	@TableField("last_update_time")
	private Date lastUpdateTime;

	@ApiModelProperty("最后更新用户id(对用户进行更新的时候添加，看数据库自己的id号,因为是你更新了)")
	@TableField("last_update_name_id")
	private String lastUpdateNameId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty("角色ID(必填，1表示管理员，2表示用户，3表示维修员，更新时候带上表示修改)")
	@TableField(exist = false)
	private Long roleId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
