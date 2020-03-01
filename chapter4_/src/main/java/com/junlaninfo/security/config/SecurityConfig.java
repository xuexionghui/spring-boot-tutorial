package com.junlaninfo.security.config;

import com.junlaninfo.entity.Permission;
import com.junlaninfo.mapper.PerssionMapper;
import com.junlaninfo.service.MyUserDetailsService;
import com.junlaninfo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 辉 on 2020/2/29.
 */
@EnableWebSecurity
@Component
public class SecurityConfig   extends WebSecurityConfigurerAdapter {
    @Autowired
    private  FailureHandler  failureHandler;

    @Autowired
    private  SuccessHandler  successHandler;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PerssionMapper permissionMapper;

    /*
    配置认证信息
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //给用户授权
  /*auth.inMemoryAuthentication().withUser("xuexionghui").password("123456").authorities(
          "showOrder","addOrder");
       //给用户admin授权
        auth.inMemoryAuthentication().withUser("admin").password("123456").
                authorities("showOrder","addOrder","updateOrder","deleteOrder");*/

    //数据库查询用户信息
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new PasswordEncoder() {
            //加密密码
            @Override
            public String encode(CharSequence charSequence) {
                String s = MD5Util.encode((String) charSequence);
                return s;
            }
            /*
            匹配
             */
            @Override             //表单输入的密码            数据库查询得到的密码
            public boolean matches(CharSequence charSequence, String s) {
                String encode = MD5Util.encode((String) charSequence);
                boolean b = encode.equals(s);
                return b;
            }
        });

    }
    /*
    配置授权信息  拦截信息
     */

    protected void configure(HttpSecurity  httpSecurity) throws Exception {
    /* httpSecurity.authorizeRequests()
                // 配置查询订单权限
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/login").permitAll()
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                .antMatchers("*").fullyAuthenticated().and().formLogin().loginPage("/login").
                successHandler(successHandler).failureHandler(failureHandler)
                .and().csrf().disable();*/

       ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = httpSecurity
              .authorizeRequests();
       // 查询数据库获取权限列表
       List<Permission> listPermission = permissionMapper.getAllPermission();
       for (Permission permission : listPermission) {
            authorizeRequests.antMatchers(permission.getUrl()).hasAuthority(permission.getPermTag());
        }
        authorizeRequests.antMatchers("/login").permitAll().antMatchers("/**").fullyAuthenticated().and().formLogin()
               .loginPage("/login").successHandler(successHandler).failureHandler(failureHandler).and().csrf()
                .disable();

    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
       return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
