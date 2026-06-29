package com.bank.account.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.bank.account.client.CustomerClient;
import com.bank.account.entity.Account;
import com.bank.account.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository repository;

    private CustomerClient customerClient;

    public AccountService(AccountRepository repository, CustomerClient customerClient) {
        this.repository = repository;
        this.customerClient = customerClient;
    }

    public Account createAccount(Account account) {
        Object customer = customerClient.getCustomer(account.getCustomerId());

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        return repository.save(account);
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);

    }

    public Account getAccount(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Account updateBalance(Long id, double amount) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double newBalance = account.getBalance() + amount;
        if (newBalance < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(newBalance);
        return repository.save(account);
    }
}