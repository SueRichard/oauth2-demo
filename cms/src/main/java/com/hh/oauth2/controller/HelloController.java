package com.hh.oauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hh
 * @version 1.0
 * @time 11/11/2023 10:33
 */
@RestController
public class HelloController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }
}
