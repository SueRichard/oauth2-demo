package com.hh.oauth2.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hh
 * @version 1.0
 * @time 11/11/2023 11:02
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有资源都需要认证
        http.authorizeRequests().antMatchers("/**").authenticated();
        //禁用csrf
        http.csrf().disable();
    }
}
