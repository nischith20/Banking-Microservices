package com.bank.transaction.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/accounts/{id}")
    Object getAccount(@PathVariable Long id);

    @PutMapping("/accounts/{id}/balance")
    Object updateBalance(@PathVariable Long id, @RequestBody double amount);
}