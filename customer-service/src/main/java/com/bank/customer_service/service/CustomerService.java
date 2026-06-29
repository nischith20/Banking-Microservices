package com.bank.customer_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.customer_service.entity.Customer;
import com.bank.customer_service.repository.CustomerRepository;

@Service
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;

    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);

    }

    public List<Customer> getCustomers() {
        return repository.findAll();

    }

    public Customer getCustomer(Long id) {
        return repository.findById(id).orElse(null);
    }

}