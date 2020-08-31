package com.xiong.common.config;

import com.xiong.common.interceptor.IdempotentTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public IdempotentTokenInterceptor idempotentTokenInterceptor(){
        return new IdempotentTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册接口幂等性拦截器
        registry.addInterceptor(idempotentTokenInterceptor());
    }

    //配置全局允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
