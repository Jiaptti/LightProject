package com.viroyal.light.module.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SysLoginController {
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/logout",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
            System.err.println(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, Model model){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }
}
