package com.viroyal.light.module.light.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2017-12-24
 */
@Data
@TableName("sys_light_info")
public class SysLightInfo extends Model<SysLightInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 路灯编码
     */
	private String code;
    /**
     * 路灯信息
     */
	private String info;
    /**
     * 1:正在使用，0:刚注册信息并没有投入使用
     */
	private String status;


	public Long getId() {
		return id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
