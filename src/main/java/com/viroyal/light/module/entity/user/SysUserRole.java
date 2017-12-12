package com.viroyal.light.module.entity.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
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
@Data
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue
	private int id;
    /**
     * 用户ID
     */
	private int uid;
    /**
     * 角色ID
     */
	private int rid;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
