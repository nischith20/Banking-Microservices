package com.bank.customer_service.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends GenericFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {

        this.jwtService = jwtService;

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)

            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String path = httpRequest.getServletPath();

        // IMPORTANT
        // skip authentication endpoints

        if (path.startsWith("/auth")) {

            chain.doFilter(request, response);

            return;

        }

        String header = httpRequest.getHeader("Authorization");

        if (header != null &&
                header.startsWith("Bearer ")) {

            String token = header.substring(7);

            String email = jwtService.extractEmail(token);

            UsernamePasswordAuthenticationToken authentication =

                    new UsernamePasswordAuthenticationToken(

                            email,

                            null,

                            null

                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        }

        chain.doFilter(request, response);

    }

}