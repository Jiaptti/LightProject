package com.viroyal.light.module.controller.user;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.module.entity.user.SysUser;
import com.viroyal.light.module.service.user.ISysRoleService;
import com.viroyal.light.module.shiro.ShiroService;
import com.viroyal.light.utils.vcode.Captcha;
import com.viroyal.light.utils.vcode.GifCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SysLoginController {

    @Autowired
    ShiroService shiroService;

    @Autowired
    ISysRoleService sysRoleService;

    //首页
    @RequestMapping(value="/index")
    public String index() {
        return "/index";
    }

    //登录
    @RequestMapping(value="/login")
    public String login() {
        return "/login";
    }

    //权限测试用
    @RequestMapping(value="/add")
    public String add() {
        return "/add";
    }

    //未授权跳转的页面
    @RequestMapping(value="/403")
    public String noPermissions() {
        return "/403";
    }

    //更新权限
    @RequestMapping(value="/updatePermission")
    @ResponseBody
    public String updatePermission() {
        shiroService.updatePermission();
        return "true";
    }

    //踢出用户
    @RequestMapping(value="/kickouting")
    @ResponseBody
    public String kickouting() {

        return "/kickout";
    }

    //被踢出后跳转的页面
    @RequestMapping(value="/kickout")
    public String kickout() {
        return "/kickout";
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
    public Map<String, Object> submitLogin(String username, String password,String vcode , Boolean rememberMe , Model model){
        System.out.print("username = " + username + " password = " + password);
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(vcode==null||vcode==""){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码不能为空！");
            return resultMap;
        }
        Session session = SecurityUtils.getSubject().getSession();
        vcode = vcode.toLowerCase();
        String v = (String) session.getAttribute("_code");
        if(!vcode.equals(v)){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码错误！");
            return resultMap;
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysUser user = null;
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            resultMap.put("status", "200");
            resultMap.put("user",user);
            if(user != null){
                String roleName = sysRoleService.getUserRoleName(user.getId());
                resultMap.put("roleName", roleName);
            }
            resultMap.put("message","登陆成功");
        } catch (Exception e){
            resultMap.put("status", "500");
            resultMap.put("user",user);
            resultMap.put("message","登录失败 error = " + e.getMessage());
            resultMap.put("roleName", "");
        }
        return JSON.toJSONString(resultMap);
    }

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="/getGifCode",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code",captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常："+e.getMessage());
        }
    }
}
