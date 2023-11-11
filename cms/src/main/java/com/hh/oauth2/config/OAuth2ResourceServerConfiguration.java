package com.hh.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author hh
 * @version 1.0
 * @time 11/11/2023 10:50
 */
@Configuration
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    //令牌校验端点，每次校验都需要提交服务器，效率低
    public static final String CHECK_TOKEN_URL = "http://localhost:8888/oauth/check_token";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        //一般项目中使用jwt基于公钥私钥验证，认证服务器使用私钥生成令牌，客户端通过公钥自己校验，而不是下面每次校验请求服务器的方式
        tokenService.setCheckTokenEndpointUrl(CHECK_TOKEN_URL);
        //设置客户端id和密钥，需要和服务端使用的客户端id和秘钥保持一致
        tokenService.setClientId("cms");
        tokenService.setClientSecret("secret");
        resources.tokenServices(tokenService);
    }
}
