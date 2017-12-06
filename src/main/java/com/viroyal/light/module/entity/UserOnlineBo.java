package com.viroyal.light.module.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserOnlineBo extends SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    //Session Id
    private String sessionId;
    //Session Host
    private String host;
    //Session创建时间
    private Date startTime;
    //Session最后交互时间
    private Date lastAccess;
    //Session timeout
    private long timeout;
    //session 是否踢出
    private boolean sessionStatus = Boolean.TRUE;

    public UserOnlineBo(SysUser obj) {}

    public UserOnlineBo() {}
}
