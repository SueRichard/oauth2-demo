package com.hh.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hh
 * @version 1.0
 * @time 11/11/2023 10:05
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hh")
                .password("{noop}123")
                .roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //静态资源放行
        web.ignoring().antMatchers("/asserts/**");
        web.ignoring().antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //配置登录页并允许访问
                .formLogin().permitAll()
                //配置basic登录，弹窗登录
//                .and().httpBasic()
                //配置登出页面
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                //配置允许匿名访问的链接
                .and().authorizeRequests().antMatchers("/oauth/**", "/login/**", "/logout/**", "/api/**").permitAll()
                //其余所有请求（地址）需要全部需要鉴权验证
                .anyRequest().authenticated()
                //关闭跨域保护
                .and().csrf().disable();
    }
}
