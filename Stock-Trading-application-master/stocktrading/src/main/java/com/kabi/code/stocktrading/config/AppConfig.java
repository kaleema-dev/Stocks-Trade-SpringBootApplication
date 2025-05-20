package com.kabi.code.stocktrading.config;

import com.kabi.code.stocktrading.util.HeaderInterceptorUtil;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages= {"com.neu.edu.stocktrading.controller"})
public class AppConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(new HeaderInterceptorUtil());
    }

}