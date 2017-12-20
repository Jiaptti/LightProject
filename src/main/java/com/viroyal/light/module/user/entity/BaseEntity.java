package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity<T extends Model> extends Model<T> {
    @TableField(exist = false)
    private int code;

    @TableField(exist = false)
    private String msg;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
