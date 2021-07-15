package com.ecomerce.ecomerce.service;

import com.ecomerce.ecomerce.model.Customer;
import com.ecomerce.ecomerce.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public Customer save(Customer customer);

    public List<Customer> findAll();

    public Customer findById(int customerId);

    public Customer update(int customerId, Customer customer);
}
