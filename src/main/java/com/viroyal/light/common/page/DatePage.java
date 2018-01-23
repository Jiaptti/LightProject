package com.viroyal.light.common.page;

import com.baomidou.mybatisplus.plugins.Page;
import com.viroyal.light.module.light.entity.SysLightInfo;
import lombok.Data;

import java.util.List;

/**
 *
 * 由此对象将page对象转换成json对象，传到前台处理
 * 由于jqgrid框架定义的page对象里面的字段和mybatisplus的不一样
 * 所以这个由这个中间对象来转换
 */
@Data
public class DatePage<T> {
    //当前页数
    private int page;

    //返回code
    private int code;

    //返回信息
    private String message;

    //每页显示数量
    private int pagesize;

    //总条数
    private int records;

    //数据列表
    private List<T> list;

    //总页数
    private int total;

    //排序字段
    private String orderByField;

    //是否升序
    private boolean isAsc;

    public DatePage(Page<T> page){
        this.page = page.getCurrent();
        this.pagesize = page.getSize();
        this.records = page.getTotal();
        this.list = page.getRecords();
        this.total = page.getPages();
        this.orderByField = page.getOrderByField();
        this.isAsc = page.isAsc();
    }
}
