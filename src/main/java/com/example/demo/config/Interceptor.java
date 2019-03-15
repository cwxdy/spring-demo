package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.Map;

/**
 * 拦截器
 */
@Component
public class Interceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long stime=System.currentTimeMillis();
        request.setAttribute("stime",stime);
        String beanName = ((HandlerMethod)handler).getBeanType().getName();
        String methodName = ((HandlerMethod)handler).getMethod().getName();
        logger.info(String.format("[接口所在Bean：%s ][函数名：%s ][入参：'%s']",beanName,methodName,null));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long etime=System.currentTimeMillis();
        request.setAttribute("etime",etime);
        String beanName = ((HandlerMethod)handler).getBeanType().getName();
        String methodName = ((HandlerMethod)handler).getMethod().getName();
        logger.info(String.format("[接口所在Bean：%s ][函数名：%s ][耗时：%s ms]",beanName,methodName,etime-Long.parseLong(request.getAttribute("stime").toString())));

    }
}
