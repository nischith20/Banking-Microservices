package com.bank.transaction.service;

import org.springframework.stereotype.Service;

import com.bank.transaction.client.AccountClient;
import com.bank.transaction.entity.Transaction;
import com.bank.transaction.repository.TransactionRepository;

@Service
public class TransactionService {

    private TransactionRepository repository;
    private AccountClient accountClient;

    public TransactionService(
            TransactionRepository repository,
            AccountClient accountClient) {

        this.repository = repository;
        this.accountClient = accountClient;

    }

    public Transaction transfer(Transaction transaction) {

        Object fromAccount = accountClient.getAccount(
                transaction.getFromAccount());

        Object toAccount = accountClient.getAccount(
                transaction.getToAccount());

        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException(
                    "Account not found");

        }

        accountClient.updateBalance(
                transaction.getFromAccount(),
                -transaction.getAmount());

        accountClient.updateBalance(
                transaction.getToAccount(),
                transaction.getAmount());

        transaction.setStatus("SUCCESS");

        return repository.save(transaction);

    }

}