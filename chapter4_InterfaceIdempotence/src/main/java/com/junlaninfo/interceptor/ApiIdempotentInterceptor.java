package com.junlaninfo.interceptor;

import com.junlaninfo.annotation.ApiIdempotent;
import com.junlaninfo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 辉 on 2020/3/8.
 * 接口幂等性校验   注解的aop拦截
 */
public class ApiIdempotentInterceptor  implements HandlerInterceptor {
    @Autowired
    TokenService  tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       if (!(handler instanceof HandlerMethod)){//不是这种类型的放行
           return  true;
       }
        HandlerMethod  handlerMethod = (HandlerMethod) handler;
        ApiIdempotent methodAnnotation = handlerMethod.getMethodAnnotation(ApiIdempotent.class);
        if (methodAnnotation!=null){
            check(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        }
        return true;
    }

    private void check(HttpServletRequest request) {
        tokenService.checkToken(request);
    }
}
