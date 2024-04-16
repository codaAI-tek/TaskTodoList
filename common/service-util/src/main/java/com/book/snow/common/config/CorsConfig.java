/**
 * Author: AKAYWJ
 * Date:2024/3/21 下午3:42
 */
package com.task.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * addMapping("/**"):配置可跨域路径
     * .allowedOrigins("*")：允许所有请求域名访问
     * .allowedHeaders("*")：允许所有请求头访问
     * .allowedMethods("GET","POST","DELETE","PUT","OPTIONS","HEAD")
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedOrigins("localhost:6001")
//                .allowedHeaders("*")
//                .allowedMethods("GET","POST","DELETE","PUT","OPTIONS","HEAD")
//                .maxAge(3600);
//    }
    // 拦截器跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域路径
        CorsRegistration cors = registry.addMapping("/**");
        // 可访问的外部域
        cors.allowedOrigins("*");
        // 支持跨域用户凭证
        cors.allowCredentials(true);
//        cors.allowedOriginPatterns("*");
        // 设置 header 能携带的信息
        cors.allowedHeaders("*");
        // 支持跨域的请求方法
        cors.allowedMethods("GET", "POST", "PUT", "DELETE");
    }


}
