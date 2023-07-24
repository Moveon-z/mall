//package com.moveon.gateway.oauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import javax.sql.DataSource;
//
///**
// * @ClassName ReactiveJdbcAuthManager
// * @Description TODO
// * @Author Moveon
// * @Date 2023/7/20 22:01
// * @Version 1.0
// **/
//// 和jdbc进行交互的核心
//@Component
//public class ReactiveJdbcAuthManager implements ReactiveAuthenticationManager {
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    public ReactiveJdbcAuthManager(DataSource dataSource) {
//        this.tokenStore = new JdbcTokenStore(dataSource);
//    }
//
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        return Mono.justOrEmpty(authentication)
//                // 原生的oauth2进行访问的时候，需要在header里添加token，并且以bearer 开头
//                .filter(a -> a instanceof BearerTokenAuthenticationToken)
//                .cast(BearerTokenAuthenticationToken.class)
//                .map(BearerTokenAuthenticationToken :: getToken)
//                .flatMap(accessToken -> {
//                    // 讲token从db中进行读取
//                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
//                    if (oAuth2AccessToken == null) {
//                        return Mono.error(new InvalidTokenException("Token not found in token store!"));
//                    }
//                    if (oAuth2AccessToken.isExpired()) {
//                        return Mono.error(new InvalidTokenException("Token is expired!"));
//                    }
//                    // 以上方式并不全面，假设有人在数据库中添加了永不过期的token，那么他就能够跳过这层校验，这显然是不合理的，因此我们需要保证token是有我们生成的。
//
//                    OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(accessToken);
//                    if (oAuth2Authentication == null) {
//                        return Mono.error(new InvalidTokenException("Invalid token!"));
//                    }
//                    if (oAuth2Authentication.isAuthenticated()) {
//                        return Mono.error(new InvalidTokenException("Token not Authenticated!"));
//                    }
//                    return Mono.justOrEmpty(oAuth2Authentication);
//                }).cast(Authentication.class);
//    }
//}
