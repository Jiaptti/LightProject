package com.viroyal.light.module.user.service.impl;

import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.page.FrontPage;
import com.viroyal.light.module.user.entity.SysPermission;
import com.viroyal.light.module.user.dao.SysPermissionMapper;
import com.viroyal.light.module.user.service.ISysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-01
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public DatePage<SysPermission> queryWithCondition(Map<String, Object> params) {
        FrontPage<SysPermission> page = new FrontPage<SysPermission>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getKey().toString().equals("pageId")){
                page.setPage(Integer.parseInt(entry.getValue().toString()));
            } else if(entry.getKey().toString().equals("pageSize")){
                page.setPageSize(Integer.parseInt(entry.getValue().toString()));
            } else {
                if(!entry.getKey().toString().equals("sort")){
                    params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                }
            }
        }
        RowBounds rowBounds = new RowBounds(page.getPage() - 1, page.getPageSize());
        page.setData(sysPermissionMapper.queryWithCondition(params, rowBounds));
        return new DatePage<SysPermission>(page.getPagePlus());
    }
}
