package com.ecomerce.ecomerce.service.impl;


import com.ecomerce.ecomerce.model.Customer;
import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.repository.CustomerRepository;
import com.ecomerce.ecomerce.repository.ItemRepository;
import com.ecomerce.ecomerce.repository.OrderRepository;
import com.ecomerce.ecomerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Order save(Order order) {
        Customer customer = this.customerRepository.findById(order.getCustomerId()).get();

        Item item = this.itemRepository.findById(order.getItemId()).get();

        order.setCustomer(customer);
        order.setItem(item);
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order findById(int orderId) {
        return null;
    }

    @Override
    public Order update(int orderId, Order order) {
        return this.orderRepository.save(order);
    }
}
