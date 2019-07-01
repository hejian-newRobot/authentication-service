package com.service.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final LettuceConnectionFactory lettuceConnectionFactory;

    private final PasswordEncoder passwordEncoder;

    /**
     * 授權的access_token有效期 一天
     */
    private static final int VALIDITY_DAY = 1;

    /**
     * token signing key
     */
    private static final String SIGNING_KEY = "hejian";

    @Autowired
    public AuthorizationServerConfiguration(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager
            , LettuceConnectionFactory lettuceConnectionFactory
            , @Qualifier("bCryptPasswordEncoder") PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.lettuceConnectionFactory = lettuceConnectionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean("redisTokenStore")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(lettuceConnectionFactory);
    }

    @Bean("jwtTokenStore")
    @ConditionalOnMissingBean(TokenStore.class)
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("test-jwt.jks"), "test123".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("test-jwt"));
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

  /*  @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }*/

    /**
     * 采用内存模型
     *
     * @param clients 客户端详情服务配置器
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}" + passwordEncoder.encode("android");
//        clients.withClientDetails(clientDetailsService);
        clients.inMemory()
                .withClient("android")
                .scopes("xx")
                .secret(finalSecret)
                .authorizedGrantTypes("password", "refresh_token")
                .and()
                .withClient("fsApp")
                .scopes("xx")
                .authorizedGrantTypes("implicit");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 1小时
        endpoints.tokenStore(redisTokenStore())
                .tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager);
        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //token store
        tokenServices.setTokenStore(endpoints.getTokenStore());
        //支持refresh_token
        tokenServices.setSupportRefreshToken(true);
        //ClientDetailsService
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        //token converter
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(VALIDITY_DAY));
        endpoints.tokenServices(tokenServices);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 允许表单认证
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
