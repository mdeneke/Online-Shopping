package com.ecomerce.ecomerce.controller;

import com.ecomerce.ecomerce.model.Customer;
import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public Customer createCustomer(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }

    @GetMapping()
    public List<Customer> getAllCustomers(){
        return this.customerService.findAll();
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomers(@PathVariable int customerId, @RequestBody Customer customer){

        return this.customerService.update(customerId, customer);
    }
}
