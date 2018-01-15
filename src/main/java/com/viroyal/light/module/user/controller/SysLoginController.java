package com.viroyal.light.module.user.controller;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.common.utils.ShiroUtils;
import com.viroyal.light.module.user.entity.SysUser;
import com.viroyal.light.module.user.entity.SysUserRole;
import com.viroyal.light.module.user.service.ISysRoleService;
import com.viroyal.light.module.user.service.ISysUserRoleService;
import com.viroyal.light.module.user.service.ISysUserService;
import com.viroyal.light.common.shiro.ShiroService;
import com.viroyal.light.common.utils.vcode.Captcha;
import com.viroyal.light.common.utils.vcode.GifCaptcha;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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

@Api("SysLoginController相关api")
@Controller
public class SysLoginController {

    @Autowired
    ShiroService shiroService;

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    ISysUserRoleService sysUserRoleService;

    //首页
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    //权限测试用
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/add";
    }

    //未授权跳转的页面
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String noPermissions() {
        return "/403";
    }

    //更新权限
    @RequestMapping(value = "/updatePermission", method = RequestMethod.GET)
    @ResponseBody
    public String updatePermission() {
        shiroService.updatePermission();
        return "true";
    }

    //踢出用户
    @RequestMapping(value = "/kickouting", method = RequestMethod.GET)
    @ResponseBody
    public String kickouting() {
        return "/kickout";
    }

    //被踢出后跳转的页面
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickout() {
        return "/kickout";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            sysUserService.logout(user.getId());
            SecurityUtils.getSubject().logout();
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGOUT_SUCCESS);
        } catch (Exception e) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGOUT_FAILURE + " : " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return resultMap;
    }

    @ApiOperation("移动端登出接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登出成功"),
            @ApiResponse(code = 500, message = "登出失败")
    })
    @RequestMapping(value = "/userLogout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> userLogout() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            sysUserService.logout(user.getId());
            SecurityUtils.getSubject().logout();
            resultMap.put(BaseConstant.CODE, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGOUT_SUCCESS);
        } catch (Exception e) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGOUT_FAILURE + " : " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return resultMap;
    }


    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, String vcode, Boolean rememberMe, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if (vcode == null || vcode == "") {
            resultMap.put(BaseConstant.STATUS, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.NULL_VERIFICATION_CODR);
            return resultMap;
        }
        Session session = ShiroUtils.getSession();
        vcode = vcode.toLowerCase();
        String v = (String) session.getAttribute("_code");
        if (!vcode.equals(v)) {
            resultMap.put(BaseConstant.STATUS, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.ERROR_VERIFICATION_CODR);
            return resultMap;
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            SecurityUtils.getSubject().login(token);
            ShiroUtils.setSessionAttribute(BaseConstant.SESSION_ATTRIBUTE, ShiroUtils.getUserId());
            resultMap.put(BaseConstant.STATUS, BaseConstant.SUCCESS_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGIN_SUCCESS);
        } catch (Exception e) {
            resultMap.put(BaseConstant.STATUS, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGIN_FAILURE + " : " + e.getMessage());
        }
        return resultMap;
    }


    @ApiOperation("移动端登录接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 500, message = "登录失败")
    })
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SysUser user = null;
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            user = ShiroUtils.getUserEntity();
            if (user.getFlag() == 0) {
                resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGIN_FAILURE + " : " + BaseConstant.USER_ACCOUNT);
            } else {
                user.setPswd(null);
                resultMap.put(BaseConstant.CODE, 200);
                resultMap.put(BaseConstant.USER, user);
                SysUserRole userRole = sysUserRoleService.selectById(user.getId());
                user.setRoleId(userRole.getRid());
                resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGIN_SUCCESS);
            }
        } catch (Exception e) {
            String errorMessage = "";
            if (e instanceof AuthenticationException) {
                errorMessage = "登录请求失败，请查看访问格式";
            } else {
                errorMessage = e.getMessage();
            }
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, BaseConstant.LOGIN_FAILURE + " : " + errorMessage);
        }
        return JSON.toJSONString(resultMap);
    }

    /**
     * 获取验证码（Gif版本）
     *
     * @param response 响应
     * @param request  请求
     */
    @RequestMapping(value = "/getGifCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 33, 4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常：" + e.getMessage());
        }
    }
}
