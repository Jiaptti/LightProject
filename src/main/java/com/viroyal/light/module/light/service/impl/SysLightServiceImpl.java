package com.viroyal.light.module.light.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysLight;
import com.viroyal.light.module.light.dao.SysLightMapper;
import com.viroyal.light.module.light.service.ISysLightService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
@Service
public class SysLightServiceImpl extends ServiceImpl<SysLightMapper, SysLight> implements ISysLightService {

    @Autowired
    SysLightMapper sysLightMapper;

    @Transactional
    @Override
    public void save(SysLight light) {
        light.setDatetime(new Date());
        sysLightMapper.save(light);
    }

    @Transactional
    @Override
    public void update(SysLight light) {
        light.setDatetime(new Date());
        sysLightMapper.update(light);
    }

    @Transactional
    @Override
    public void deleteBatch(Object[] ids) {
        sysLightMapper.deleteBatch(ids);
    }

    @Override
    public DatePage<SysLight> queryWithCondition(Map<String, Object> params) {
        Page<SysLight> page = new Page<SysLight>();
        int pageId = 0, pageSize = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getKey().toString().equals("pageId")){
                pageId = Integer.parseInt(entry.getValue().toString());
            } else if(entry.getKey().toString().equals("pageSize")){
                pageSize = Integer.parseInt(entry.getValue().toString());
            } else {
                if(NumberUtils.isNumber(entry.getValue().toString())){
                    params.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
                }
            }
        }
        if(pageId == 0 || pageSize == 0){
            List<SysLight> data = sysLightMapper.queryWithCondition(params);
            page.setSize(data.size());
            page.setTotal(data.size());
            page.setRecords(data);
        } else {
            page.setCurrent(pageId);
            page.setSize(pageSize);
            page.setRecords(sysLightMapper.queryWithCondition(params, page));
        }
        return new DatePage<SysLight>(page);
    }
}
