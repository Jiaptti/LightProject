package com.viroyal.light.module.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.user.entity.SysRole;
import com.viroyal.light.module.user.dao.SysRoleMapper;
import com.viroyal.light.module.user.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Transactional
    @Override
    public void save(SysRole role) {
        sysRoleMapper.save(role);
    }

    @Transactional
    @Override
    public void update(SysRole role) {
        sysRoleMapper.update(role);
    }


    @Override
    public void deleteBatch(Object[] ids) {
        sysRoleMapper.deleteBatch(ids);
    }

    @Override
    public DatePage<SysRole> queryWithCondition(Map<String, Object> params) {
        Page<SysRole> page = new Page<SysRole>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getKey().toString().equals("pageId")){
                page.setCurrent(Integer.parseInt(entry.getValue().toString()));
            } else if(entry.getKey().toString().equals("pageSize")){
                page.setSize(Integer.parseInt(entry.getValue().toString()));
            } else {
                if(NumberUtils.isNumber(entry.getValue().toString())){
                    params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                }
            }
        }
        page.setRecords(sysRoleMapper.queryWithCondition(params, page));
        return new DatePage<SysRole>(page);
    }
}
