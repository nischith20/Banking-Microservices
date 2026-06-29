package com.bank.customer_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.bank.customer_service.entity.Customer;
import com.bank.customer_service.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer createCustomer(
            @RequestBody Customer customer) {
        return service.saveCustomer(customer);

    }

    @GetMapping
    public List<Customer> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return service.getCustomer(id);
    }

}