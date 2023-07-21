package com.moveon.gateway.oauth2.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @ClassName AccessManager
 * @Description 进行我们访问的放行或者是拦截
 * @Author Moveon
 * @Date 2023/7/19 23:43
 * @Version 1.0
 **/
@Component
public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    // 对于url的check，有些url需要从gateway层面直接放行。
    // 存放可以过滤的路径
    private Set<String> permitAll = new ConcurrentSkipListSet<>();
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    public AccessManager() {
        permitAll.add("/**/oauth/**");
    }

    private boolean checkPath(String requestUrl) {
        return permitAll.stream()
                .filter(p -> ANT_PATH_MATCHER.match(p, requestUrl)).findFirst().isPresent();
    }

    // 最终的决定权
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        String requestUrl = exchange.getRequest().getURI().getPath();
        return authentication.map(auth -> {
            if (checkPath(requestUrl)) {
                return new AuthorizationDecision(true);
            }
            if (auth instanceof OAuth2Authentication) {
                OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) auth;
                String clientId = oAuth2Authentication.getOAuth2Request().getClientId();
                if (StringUtils.isNotEmpty(clientId)) {
                    return new AuthorizationDecision(true);
                }
            }
            return new AuthorizationDecision(false);
        });
    }
}
