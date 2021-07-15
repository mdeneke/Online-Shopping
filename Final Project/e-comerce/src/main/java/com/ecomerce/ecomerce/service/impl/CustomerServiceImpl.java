package com.ecomerce.ecomerce.service.impl;

import com.ecomerce.ecomerce.model.Address;
import com.ecomerce.ecomerce.model.Customer;
import com.ecomerce.ecomerce.repository.AddressRepository;
import com.ecomerce.ecomerce.repository.CustomerRepository;
import com.ecomerce.ecomerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Customer save(Customer customer) {
        Address address = this.addressRepository.save(customer.getAddress());
        customer.setAddress(address);
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(int customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        return customer.get();
    }

    @Override
    public Customer update(int customerId, Customer customer) {


        return  this.customerRepository.save(customer);
    }
}
