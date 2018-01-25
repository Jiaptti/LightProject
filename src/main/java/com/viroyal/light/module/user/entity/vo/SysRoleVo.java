package com.viroyal.light.module.user.entity.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="角色信息")
@Data
public class SysRoleVo {

    @ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称(添加时候必填，更新不用)")
    private String name;

    /**
     * 角色类型
     */
    @ApiModelProperty("角色类型(添加时候必填，更新不用)")
    private String type;
}
