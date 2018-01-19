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
import java.io.Serializable;

/**
 * <p>
 * 权限实体类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */

@Data
@ApiModel(value="权限信息")
@TableName("sys_permission")
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * url地址
     */
	@ApiModelProperty("url地址(服务器这边表示可以访问的页面地址，apk端不需要)(添加时候必填，更新不用)")
	private String url;
    /**
     * url描述
     */
	@ApiModelProperty("权限名(xx列表，xx添加，xx删除，xx更新，参考数据库)(添加时候必填，更新不用)")
	@NotNull(message = "{permission.name.not.empty}")
	private String name;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)(添加时候必填，更新不用)")
	@NotNull(message = "{permission.perms.not.empty}")
	@TableField("perms")
	private String perms;

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
