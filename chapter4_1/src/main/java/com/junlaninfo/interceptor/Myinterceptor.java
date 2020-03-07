package com.junlaninfo.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 辉 on 2020/3/7.
 * 配置拦截器的信息
 */
public class Myinterceptor implements HandlerInterceptor {


    private static final Logger log = LoggerFactory.getLogger(Myinterceptor.class);

    /*
       在controller前执行，返回true执行controller
     * 预处理回调函数
     * 进入Controller之前执行
     * 如果返回 true，则进入下一个拦截器，所有拦截器全部通过，则进入 Controller 相应的方法
     * 如果返回 false，则请求被拦截。
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------------------------------");
        log.info(request.getRequestURL() + "");
        log.info("----------------------------------");
        return true;
    }

    /*
      执行完controller执行此方法
       后处理回调方法
     * 经过Controller处理之后执行
     * 在此处可以对模型数据进行处理或对视图进行处理
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      log.info("已经执行完controller");
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调
     * 在此处可以进行一些资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("看这个方法啥时候执行");
    }
}
