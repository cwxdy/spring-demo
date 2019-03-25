package com.example.demo.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
/**
 * 路由控制，控制某些路由需要进入拦截器
 * 注意：Shiro的拦截高于HandlerInterceptor
 */
@Configuration
public class WebMvcListener implements WebMvcConfigurer {
    @Autowired
    private Interceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login/**");
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
