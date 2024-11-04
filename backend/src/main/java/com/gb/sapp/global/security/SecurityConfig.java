package com.gb.sapp.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(
                        csrf -> csrf.disable()
                )
                .cors(
                        cors -> cors.configurationSource(
                                httpServletRequest -> {
                                    var corsConfiguration = new CorsConfiguration();
                                    //corsConfiguration.setAllowCredentials(true);
                                    corsConfiguration.setAllowedOrigins(List.of("https://cdpn.io"));
                                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
                                    corsConfiguration.setAllowedHeaders(List.of("*"));
                                    return corsConfiguration;
                                }
                        )
                );
        return httpSecurity.build();
    }
}
