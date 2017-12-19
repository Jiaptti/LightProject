package com.viroyal.light.module.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity<T extends Model> extends Model<T> {
    private int code;

    private String msg;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
