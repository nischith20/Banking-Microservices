package com.bank.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")

public interface CustomerClient {

    @GetMapping("/customers/{id}")
    Object getCustomer(@PathVariable Long id);

}