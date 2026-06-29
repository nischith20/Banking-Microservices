package com.bank.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.customer_service.entity.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    User findByEmail(String email);

}