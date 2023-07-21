package com.moveon.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @ClassName WebSecurityConfigs
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/18 22:55
 * @Version 1.0
 **/
@Configuration
public class WebSecurityConfigs {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .antMatcher("/swagger-*")
                .antMatcher("/document-*")
                .authorizeRequests().anyRequest().authenticated().and()
                .httpBasic()
                .and().cors()
                .and().csrf().disable()
                .build();
    }
}
