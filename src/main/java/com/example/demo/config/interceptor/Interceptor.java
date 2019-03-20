package com.example.demo.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.Consants;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.entity.User;
import com.example.demo.utils.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
/**
 * 拦截器
 */
@Configuration
public class Interceptor implements HandlerInterceptor {



    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisUtil redisUtil;
    @Value("${spring.session.timeout}")
    private long timeout;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long stime=System.currentTimeMillis();
        request.setAttribute("stime",stime);
        String beanName = ((HandlerMethod)handler).getBeanType().getName();
        String methodName = ((HandlerMethod)handler).getMethod().getName();
        logger.info(String.format("[接口所在Bean：%s ][函数名：%s ][入参：'%s']",beanName,methodName,null));
        String token=request.getHeader("token");
        if(token==null){
            throw new RuntimeException("缺少token令牌");
        }
        authOnline(token,request);
        return true;
    }

    /**
     * 登录状态校验
     * @param token
     */
    private void authOnline(String token,HttpServletRequest request) {
        if(redisUtil.existsKey(token)){
            redisUtil.expireKey(token,timeout, TimeUnit.MINUTES);

        }else{
            throw new RuntimeException("请先登录");
        }
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
