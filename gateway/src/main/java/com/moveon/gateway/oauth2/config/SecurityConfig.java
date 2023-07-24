//package com.moveon.gateway.oauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
//
//import javax.sql.DataSource;
//
///**
// * @ClassName SecurityConfig
// * @Description TODO
// * @Author Moveon
// * @Date 2023/7/20 23:16
// * @Version 1.0
// **/
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private AccessManager accessManager;
//
//    @Bean
//    public SecurityWebFilterChain webFluxSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
//        ReactiveJdbcAuthManager reactiveJdbcAuthManager = new ReactiveJdbcAuthManager(dataSource);
//        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(reactiveJdbcAuthManager);
//        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
//
//        return serverHttpSecurity
//                // 响应式编程 和rest编程不一样
//                .httpBasic().disable()
//                .csrf().disable()
//                // OPTIONS 为了兼容ajax调用
//                .authorizeExchange().pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyExchange().access(accessManager)
//                .and()
//                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//                .build();
//    }
//
//}
