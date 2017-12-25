package com.viroyal.light.common.filter;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		Subject currentUser = SecurityUtils.getSubject();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Logger.getLogger(getClass()).info("currentUser = " + currentUser + " req = " + req);
		System.out.print("12312312312312321uri = " + request.getRequestURI());
		if (!currentUser.isAuthenticated()) {
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
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}
