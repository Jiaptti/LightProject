package com.viroyal.light.common.page;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.util.List;

/**
 * 用来接收页面传过来的查询字段   对象
 *
 */
@Data
public class FrontPage<T> {
    //是否是查询
    private boolean _search;

    //时间戳（毫秒）
    private String nd;

    private List<T> data;

    //每页显示条数
    private int rows;

    //当前页数
    private int page;

    //排序的字段
    private String sidx;

    private int pageSize;

    //排序方式 asc升序  desc降序
    private String sort;

    //搜索条件
    private String keywords;

    //获取mybatisPlus封装的Page对象
    public Page<T> getPagePlus(){
        Page<T> pagePlus = new Page<T>();
        pagePlus.setCurrent(this.page);
        pagePlus.setSize(rows);
        pagePlus.setRecords(data);
        if(sort == null){
            sort="asc";
        }
        pagePlus.setAsc(this.sort.equals("asc"));
        pagePlus.setOrderByField(this.sidx);
        return pagePlus;
    }
}
