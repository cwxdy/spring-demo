package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
/**
 * 路由控制，控制某些路由需要进入拦截器
 */
@Configuration
public class WebMvcListener implements WebMvcConfigurer {
    @Autowired
    private Interceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/api/**");
    }

    /**
     * 静态资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO: 3/19/2019  
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/templates/");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/static/");
    }
}
