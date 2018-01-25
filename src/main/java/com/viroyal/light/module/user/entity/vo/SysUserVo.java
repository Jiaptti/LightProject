package com.viroyal.light.module.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户信息")
public class SysUserVo {

    @ApiModelProperty("主键id,自增长(不用填，更新的时候带上)")
    private Long id;
    /**
     * 用户昵称
     */
    @ApiModelProperty("用户姓名(添加时候必填，更新不用)")
    private String nickname;

    /**
     * 用户账号
     */
    @ApiModelProperty("用户账号(添加时候必填，更新不用)")
    private String username;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码(添加时候必填，更新不用)")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱(添加时候选填，更新不用)")
    private String email;
    /**
     * 密码
     */
    @ApiModelProperty("密码(添加时候必填，更新不用，有专门修改密码的接口)")
    private String pswd;
    /**
     * 1:有效，0:禁止登录
     */
    @ApiModelProperty("用户状态(1:有效，0:禁止登录,添加时候必填，更新选填)")
    private String status;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID(必填，1表示管理员，2表示用户，3表示维修员，更新时候带上表示修改)")
    private Long roleId;
}
