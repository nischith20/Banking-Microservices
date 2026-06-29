package com.bank.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        SecurityWebFilterChain securityFilterChain(
                        ServerHttpSecurity http) {

                return http

                                .csrf(csrf -> csrf.disable())

                                .authorizeExchange(auth -> auth

                                                .pathMatchers("/auth/**")
                                                .permitAll()

                                                .anyExchange()
                                                .permitAll()

                                )

                                .build();

        }

}