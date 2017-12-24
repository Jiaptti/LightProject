package com.viroyal.light.module.light.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.module.light.entity.SysLight;
import com.viroyal.light.module.light.service.ISysLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiaptti
 * @since 2017-12-24
 */
@Controller
@RequestMapping("/light")
public class SysLightController {
    @Autowired
    ISysLightService sysLightService;


    @RequestMapping("/list")
    @ResponseBody
    public String getLightList(){
        List<SysLight> list =  sysLightService.selectByMap(new HashMap<>());
        return JSON.toJSONString(list);
    }
}
