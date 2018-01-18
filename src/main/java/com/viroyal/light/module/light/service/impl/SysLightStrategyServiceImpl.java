package com.viroyal.light.module.light.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.viroyal.light.common.page.DatePage;
import com.viroyal.light.common.utils.NumberUtils;
import com.viroyal.light.module.light.entity.SysLightStrategy;
import com.viroyal.light.module.light.dao.SysLightStrategyMapper;
import com.viroyal.light.module.light.service.ISysLightStrategyService;
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
 * @since 2018-01-04
 */
@Service
public class SysLightStrategyServiceImpl extends ServiceImpl<SysLightStrategyMapper, SysLightStrategy> implements ISysLightStrategyService {

    @Autowired
    SysLightStrategyMapper sysLightStrategyMapper;

    @Transactional
    @Override
    public void save(SysLightStrategy lightStrategy) {
        sysLightStrategyMapper.save(lightStrategy);
    }

    @Transactional
    @Override
    public void update(SysLightStrategy lightStrategy) {
        sysLightStrategyMapper.update(lightStrategy);
    }

    @Transactional
    @Override
    public void deleteBatch(Object[] ids) {
        sysLightStrategyMapper.deleteBatch(ids);
    }


    @Override
    public DatePage<SysLightStrategy> queryWithCondition(Map<String, Object> params) {
        Page<SysLightStrategy> page = new Page<SysLightStrategy>();
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
            List<SysLightStrategy> data = sysLightStrategyMapper.queryWithCondition(params);
            page.setSize(data.size());
            page.setTotal(data.size());
            page.setRecords(data);
        } else {
            page.setCurrent(pageId);
            page.setSize(pageSize);
            page.setRecords(sysLightStrategyMapper.queryWithCondition(params, page));
        }
        return new DatePage<SysLightStrategy>(page);
    }
}
