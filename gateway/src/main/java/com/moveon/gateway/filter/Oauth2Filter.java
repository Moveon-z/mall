package com.moveon.gateway.filter;

import com.moveon.gateway.feign.Oauth2FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName Oauth2Filter
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/21 22:37
 * @Version 1.0
 **/
@Component
public class Oauth2Filter implements GlobalFilter, Ordered {

    //check token rest 访问
    @Autowired
    @Lazy
    private Oauth2FeignClient oauth2FeignClient;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // pathUrl校验
        String requestUrl = request.getURI().getPath();
        if (requestUrl.contains("/user/register")) {
            return chain.filter(exchange);
        }

        // path校验通过后 看token
        String token = request.getHeaders().getFirst("Authorization");
        Map<String, Objects> tokenResult = oauth2FeignClient.checkToken(token);
        if (!Boolean.valueOf(String.valueOf(tokenResult.get("active")))) {
            // 错误的处理
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String tracingId = UUID.randomUUID().toString().replace("-", "");
        ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
            httpHeaders.set("tracingId", tracingId);
        }).build();
        exchange.mutate().request(serverHttpRequest);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
