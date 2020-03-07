package com.junlaninfo.webmvc;

import com.junlaninfo.interceptor.Myinterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Created by 辉 on 2020/3/7.
 * 将自定义的浏览器加入到springboot里面
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Myinterceptor myinterceptor() {
        return new Myinterceptor();
    }

    /*
      将定义好的拦截器添加进去
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myinterceptor());
    }

}
