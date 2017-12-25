package com.viroyal.light.common.filter;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.ShiroUtils;
import com.viroyal.light.module.user.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        try {
            if (StringUtils.contains(uri, "login")
                    || !StringUtils.startsWith(uri, "/user")) {
                Logger.getLogger(getClass()).info("not filter interface");
                chain.doFilter(request, response);
            } else {
                SysUser sysUser = ShiroUtils.getUserEntity();
                if (sysUser != null && StringUtils.isNotBlank(sysUser.getId()+"")) {
                    Logger.getLogger(getClass()).info("user is exist");
                    chain.doFilter(request, response);
                } else {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    Map<String,Object> resultMap = new HashMap<String,Object>();
                    resultMap.put("code" , 500);
                    resultMap.put("message", "userid is null, please Login");
                    Logger.getLogger(getClass()).info("userid is null");
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.write(JSON.toJSONString(resultMap));
                    } catch (Exception e) {
                        Logger.getLogger(getClass()).info("washingException", e);
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass()).info("UserExistFilter exception");
            Logger.getLogger(getClass()).info("washingException", e);
        }
    }

    @Override
    public void destroy() {

    }
}
