package com.junlaninfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 辉 on 2020/2/29.
 */
@SpringBootApplication
@MapperScan("com.junlaninfo.mapper")
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
        // Security 两种模式 fromLogin 表单提交认证模式 httpBasic 浏览器与服务器做认证授权
    }
}
