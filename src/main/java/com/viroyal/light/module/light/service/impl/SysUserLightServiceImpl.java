package com.viroyal.light.module.light.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.viroyal.light.module.light.entity.SysUserLight;
import com.viroyal.light.module.light.dao.SysUserLightMapper;
import com.viroyal.light.module.light.service.ISysUserLightService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
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
public class SysUserLightServiceImpl extends ServiceImpl<SysUserLightMapper, SysUserLight> implements ISysUserLightService {

    @Autowired
    SysUserLightMapper userLightMapper;

    @Transactional
    @Override
    public void dispatchToUser(SysUserLight userLight) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("light_id", userLight.getLightId());
        List<SysUserLight> userLightList = userLightMapper.selectByMap(map);
        if(userLightList.size() == 0){
            userLightMapper.insert(userLight);
        } else {
            Wrapper<SysUserLight> wrapper = new EntityWrapper<SysUserLight>();
            wrapper.eq("light_id", userLight.getLightId());
            userLightMapper.update(userLight, wrapper);
        }
    }
}
