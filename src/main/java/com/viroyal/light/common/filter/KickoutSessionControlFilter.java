package com.viroyal.light.common.filter;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.AjaxResponseWriter;
import com.viroyal.light.common.utils.BaseConstant;
import com.viroyal.light.module.user.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

/**
 * 1.读取当前登录用户名，获取在缓存中的sessionId队列
 * 2.判断队列的长度，大于最大登录限制的时候，按踢出规则
 *  将之前的sessionId中的session域中存入kickout：true，并更新队列缓存
 * 3.判断当前登录的session域中的kickout如果为true，
 * 想将其做退出登录处理，然后再重定向到踢出登录提示页面
 */
public class KickoutSessionControlFilter extends AccessControlFilter{

    private SessionManager sessionManager;

    private String kickoutUrl; //踢出后到的地址

    private int maxSesion = 1;//最大登录数

    private boolean kickoutAfter = false; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户

    private Cache<String, Deque<Serializable>> cache; //缓存

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setMaxSession(int maxSesion) {
        this.maxSesion = maxSesion;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setCacheManager(CacheManager cacheManager){
        cache = cacheManager.getCache("shiro_redis_cache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }



    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String value = request.getHeader("User-Agent");
        if((!StringUtils.isEmpty(value) && value.contains("Android"))
                && !subject.isAuthenticated()){
            //App端口
            AjaxResponseWriter.write(request, response, BaseConstant.ERROR_CODE, "请刷新界面重新登陆");
            return false;
        } else if(!subject.isAuthenticated()){
            //如果没有登录，直接进行之后的流程
            return true;
        }
        SysUser user = (SysUser) subject.getPrincipal();
        Session session = subject.getSession();

        //读取缓存   没有就存入
        Deque<Serializable> deque = cache.get(user.getUsername());

        //如果此用户没有session队列，也就是还没有登录过，缓存中没有
        //就new一个空队列，不然deque对象为空，会报空指针
        if(deque==null){
            deque = new LinkedList<Serializable>();
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(session.getId()) && session.getAttribute("kickout") == null){
            //将sessionId存入队列
            deque.push(session.getId());
            //将用户的sessionId队列缓存
            cache.put(user.getUsername(), deque);
        }


        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSesion){
            Serializable sessionId = null;
            if(kickoutAfter){
                //踢出后者
                sessionId = deque.removeFirst();
            } else {
                //踢出前者
                sessionId = deque.removeLast();
            }
            //踢出后再更新下缓存队列
            cache.put(user.getUsername(), deque);

            try {
                //获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
                if(kickoutSession != null){
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if(session.getAttribute("kickout") != null &&(Boolean)session.getAttribute("kickout") == true){
            //会话被踢出了
            try {
                //退出登录
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(servletRequest);

            Map<String, String> resultMap = new HashMap<String, String>();
            //判断是不是Ajax请求
            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) servletRequest).getHeader("X-Requested-With"))) {
                //输出json串
                AjaxResponseWriter.write(request, response, BaseConstant.ERROR_CODE, "您已经在其他地方登录，请重新登录！");
            }else{
                //重定向
                WebUtils.issueRedirect(servletRequest, servletResponse, kickoutUrl);
            }
            return false;
        }
        return true;
    }

    private void out(ServletResponse hresponse, Map<String, String> resultMap)
            throws IOException {
        try {
            hresponse.setCharacterEncoding("UTF-8");
            PrintWriter out = hresponse.getWriter();
            out.println(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
        }
    }
}
