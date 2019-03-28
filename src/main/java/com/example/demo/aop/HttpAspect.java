package com.example.demo.aop;

import com.example.demo.base.BaseController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChangYu
 * @Date: 3/28/2019 11:08 AM
 * @Version 1.0
 */
@Aspect
@Component
public class HttpAspect {
    public static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void paramsLog(){

    }

    @Before("paramsLog()")
    public void dobefore(JoinPoint joinPoint){
        ServletRequestAttributes arrt = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = arrt.getRequest();
        logger.info("请求地址 url:{}",request.getRequestURL());
        logger.info("请求方式 ：{}",request.getMethod());
        logger.info("请求者ip地址：{}",request.getRemoteAddr());
        logger.info("访问的类与方法：{}",joinPoint.getSignature().getDeclaringTypeName()+"."
                +joinPoint.getSignature().getName());
        logger.info("请求参数：{}",joinPoint.getArgs());
    }

}
