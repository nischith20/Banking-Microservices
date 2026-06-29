package com.bank.api_gateway.security;

import org.springframework.stereotype.Component;

import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements GlobalFilter {

        private final JwtService jwtService;

        public JwtFilter(JwtService jwtService) {

                this.jwtService = jwtService;

        }

        @Override
        public Mono<Void> filter(
                        ServerWebExchange exchange,
                        GatewayFilterChain chain) {

                String path = exchange.getRequest()
                                .getURI()
                                .getPath();

                if (path.startsWith("/auth")) {

                        return chain.filter(exchange);

                }

                String header = exchange.getRequest()
                                .getHeaders()
                                .getFirst("Authorization");

                if (header == null ||
                                !header.startsWith("Bearer ")) {

                        exchange.getResponse()
                                        .setStatusCode(
                                                        HttpStatus.UNAUTHORIZED);

                        return exchange.getResponse()
                                        .setComplete();

                }

                try {

                        String token = header.substring(7);

                        jwtService.extractEmail(token);

                        return chain.filter(exchange);

                } catch (Exception e) {

                        exchange.getResponse()
                                        .setStatusCode(
                                                        HttpStatus.UNAUTHORIZED);

                        return exchange.getResponse()
                                        .setComplete();

                }

        }

}