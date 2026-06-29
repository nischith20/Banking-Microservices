package com.bank.transaction.controller;

import org.springframework.web.bind.annotation.*;

import com.bank.transaction.entity.Transaction;
import com.bank.transaction.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService service;

    public TransactionController(
            TransactionService service) {

        this.service = service;

    }

    @PostMapping("/transfer")
    public Transaction transfer(
            @RequestBody Transaction transaction) {

        return service.transfer(transaction);

    }

}