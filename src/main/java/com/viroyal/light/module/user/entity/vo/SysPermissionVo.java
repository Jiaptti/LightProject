package com.viroyal.light.module.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="权限信息")
public class SysPermissionVo {

    @ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
    private Long id;
    /**
     * url描述
     */
    @ApiModelProperty("权限名(xx列表，xx添加，xx删除，xx更新，参考数据库)(添加时候必填，更新不用)")
    private String name;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)(添加时候必填，更新不用)")
    private String perms;
}
