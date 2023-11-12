package com.hh.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author hh
 * @version 1.0
 * @time 11/11/2023 09:16
 */
@Configuration
//开启授权服务
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    /**
     * spring-security认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    public static final String CLIENT_ID = "cms";
    /**
     * spring-security5默认进行密码加密
     * {noop} no operation password表示不对密码加密，使用明文密码
     */
    public static final String SECRET_CHAR_SEQUENCE = "{noop}secret";
    /**
     * 授权范围
     */
    public static final String SCOPE_READ = "read";
    /**
     * 授权范围
     */
    public static final String SCOPE_WRITE = "write";
    /**
     * 授权范围
     */
    public static final String TRUST = "trust";
    /**
     * 授权范围
     */
    public static final String USER = "user";
    /**
     * 授权范围
     */
    public static final String ALL = "all";
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 30 * 60;
    public static final int REFRESH_TOKEN_VALIDITY_SECONDS = 30 * 60;
    /**
     * 授权模式
     * 密码模式授权模式
     */
    public static final String GRANT_TYPE_PASSWORD = "password";
    /**
     * 授权模式
     * 授权码模式
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";
    /**
     * refresh_token
     */
    public static final String REFRESH_TOKEN = "refresh_token";
    /**
     * 授权模式
     * 简化授权模式
     */
    public static final String IMPLICIT = "implicit";
    /**
     * 授权模式
     * 客户端模式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    /**
     * 指定哪些资源是需要授权验证的
     */
    public static final String RESOURCE_ID = "resource_id";

    /**
     * 配置客户端信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                //使用内存存储
                .inMemory()
                //客户端id，授权模式
                .withClient(CLIENT_ID)
                //客户端秘钥
                .secret(SECRET_CHAR_SEQUENCE)
                //自动授权，授权成功返回授权码
                .autoApprove(true)
                //授权后重定向地址
                .redirectUris("http://localhost:8084/cms/login")
                //允许授权的范围
                .scopes(ALL)
                //访问令牌的时效
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                //刷新令牌的时效
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                //允许授权的类型
                .authorizedGrantTypes(AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT, GRANT_TYPE_PASSWORD, CLIENT_CREDENTIALS);
    }

    /**
     * token的存储方式
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(memoryTokenStore());
    }

    /**
     * oauth2端点的权限配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //配置 /oauth/token_key/ 匿名访问
                .tokenKeyAccess("permitAll()")
                //设置认证后访问
//                .tokenKeyAccess("isAuthenticated()")
                //开启令牌验证端点 /oauth/check_token 匿名访问
                .checkTokenAccess("permitAll()")
                //允许表单验证
                .allowFormAuthenticationForClients();
    }

    /**
     * 上面调用的是这个方法，所以可以不需要@bean注解
     *
     * @return
     */
    @Bean
    public TokenStore memoryTokenStore() {
        //使用内存存储和生成token/令牌
        return new InMemoryTokenStore();
    }
}
