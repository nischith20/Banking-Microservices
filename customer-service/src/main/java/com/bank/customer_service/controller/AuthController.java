package com.bank.customer_service.controller;

import org.springframework.web.bind.annotation.*;

import com.bank.customer_service.security.JwtService;
import com.bank.customer_service.entity.User;
import com.bank.customer_service.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

        private UserRepository repository;

        private JwtService jwtService;

        public AuthController(UserRepository repository, JwtService jwtService) {

                this.repository = repository;
                this.jwtService = jwtService;
        }

        @PostMapping("/register")
        public User register(
                        @RequestBody User user) {

                return repository.save(user);

        }

        @PostMapping("/login")
        public String login(
                        @RequestBody User user) {

                User existing = repository.findByEmail(
                                user.getEmail());

                if (existing == null ||
                                !existing.getPassword()
                                                .equals(user.getPassword())) {

                        throw new RuntimeException(
                                        "Invalid login");

                }

                return jwtService.generateToken(
                                user.getEmail(), existing.getRole());

        }

}