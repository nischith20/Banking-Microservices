package com.bank.account.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.bank.account.entity.Account;
import com.bank.account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.createAccount(account);

    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable Long customerId) {
        return service.getAccountsByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @PutMapping("/{id}/balance")
    public Account updateBalance(@PathVariable Long id, @RequestBody double amount) {
        return service.updateBalance(id, amount);
    }
}