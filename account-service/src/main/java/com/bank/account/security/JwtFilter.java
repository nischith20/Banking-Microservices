package com.bank.account.security;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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

        String header = httpRequest.getHeader("Authorization");

        if (header != null &&
                header.startsWith("Bearer ")) {

            String token = header.substring(7);

            String email = jwtService.extractEmail(token);

            String role = jwtService.extractRole(token);

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(authority));

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);

        }

        chain.doFilter(request, response);

    }

}