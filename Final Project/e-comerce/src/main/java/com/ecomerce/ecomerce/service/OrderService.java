package com.ecomerce.ecomerce.service;

import com.ecomerce.ecomerce.model.Address;
import com.ecomerce.ecomerce.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Order save(Order order);

    public List<Order> findAll();

    public Order findById(int orderId);

    public Order update(int orderId, Order order);
}
