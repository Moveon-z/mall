package com.moveon.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Objects;

/**
 * @ClassName Oauth2FeignClient
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/21 22:38
 * @Version 1.0
 **/
@FeignClient("oauth2-service")
public interface Oauth2FeignClient {

    @RequestMapping("/oauth/check_token")
    Map<String, Objects> checkToken(String token);
}
