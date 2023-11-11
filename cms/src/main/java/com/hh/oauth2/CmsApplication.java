package com.hh.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author hh
 * @version 1.0
 * @time 11/11/2023 10:28
 */
@SpringBootApplication
//开启资源服务器
@EnableResourceServer
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
