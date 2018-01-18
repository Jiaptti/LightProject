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
@ApiModel(value="用户信息")
@Data
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户昵称
     */
	@ApiModelProperty("用户姓名(添加时候必填，更新不用)")
	@NotNull(message = "{user.nickname.not.empty}")
	@Pattern(regexp = "[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*", message = "{user.nickname.right.format}")
	@Size(min=2, max=5, message="{user.nickname.length}")
	private String nickname;

	/**
	 * 用户账号
	 */
	@ApiModelProperty("用户账号(添加时候必填，更新不用)")
	@NotNull(message = "{user.username.not.empty}")
	@Size(min=5, max=10, message="{user.username.length}")
	private String username;

	/**
	 * 手机号码
	 */
	@ApiModelProperty("手机号码(添加时候必填，更新不用)")
	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "{user.phone.right.format}")
	private String phone;
    /**
     * 邮箱
     */
	@ApiModelProperty("邮箱(添加时候选填，更新不用)")
	@Pattern(regexp="[a-za-z0-9._%+-]+@[a-za-z0-9.-]+\\.[a-za-z]{2,4}", message="{user.email.right.format}")
	private String email;
    /**
     * 密码
     */
    @NotEmpty(message = "{user.password.not.empty}")
	@Size(min=5, max=20, message="{user.password.length}")
	@ApiModelProperty("密码(添加时候必填，更新不用)，记得加密")
	private String pswd;
    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间(不必添加)")
	@TableField("create_time")
	private Date createTime;
    /**
     * 最后登录时间
     */
	@ApiModelProperty("最后登录时间(不必填)")
	@TableField("last_login_time")
	private Date lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
	@ApiModelProperty("用户状态(1:有效，0:禁止登录,添加时候必填，更新选填)")
	private String status;

	@ApiModelProperty("创建用户的id(是谁创建了这个用户，添加的时候，看数据库自己的id号,因为是你创建)(添加时候必填，更新不用)")
	@TableField("create_name_id")
	private String createNameId;

	@ApiModelProperty("最后修改时间(不必填)")
	@TableField("last_update_time")
	private Date lastUpdateTime;

	@ApiModelProperty("最后更新用户id(对用户进行更新的时候添加，看数据库自己的id号,因为是你更新了)(添加时候必填，更新不用)")
	@TableField("last_update_name_id")
	private String lastUpdateNameId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty("角色ID(必填，1表示管理员，2表示用户，3表示维修员，更新时候带上表示修改)")
	@TableField(exist = false)
	@NotNull(message = "{user.roleId.not.empty}")
	private Long roleId;

	/**
	 * 是否可见
	 */
	@ApiModelProperty("用来标记删除的不需要添加，默认为1")
	@TableField("exist")
	private Integer exist;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
