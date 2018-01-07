package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2018-01-04
 */
@Data
@TableName("sys_light_group")
public class SysLightGroup extends Model<SysLightGroup> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 组id
     */
	@TableField("group_id")
	private Integer groupId;

    /**
     * 组名
     */
	@TableField("group_name")
	private String groupName;
    /**
     * 由谁创建的组
     */
	@TableField("create_user_id")
	private Long createUserId;
    /**
     * 谁负责的组
     */
	@TableField("responsible_id")
	private Long responsibleId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
