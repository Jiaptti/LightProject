package com.viroyal.light.common.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		Subject currentUser = SecurityUtils.getSubject();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		System.out.print("currentUser.isAuthenticated() = " + currentUser.isAuthenticated()
					+ " uri = " + request.getRequestURI());
		if (!currentUser.isAuthenticated()) {
//			AjaxResponseWriter.write(req, res, ServiceStatusEnum.UNLOGIN, "请登录");
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}
