package com.viroyal.light.module.entity.user;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("sys_user")
@Data
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 用户昵称
     */
	private String nickname;
    /**
     * 邮箱|登录帐号
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
	private Date createTime;
    /**
     * 最后登录时间
     */
	@TableField("last_login_time")
	private Date lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
	private String status;
	@TableField("create_name_id")
	private String createNameId;
	@TableField("last_update_time")
	private Date lastUpdateTime;
	@TableField("last_update_name_id")
	private String lastUpdateNameId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
