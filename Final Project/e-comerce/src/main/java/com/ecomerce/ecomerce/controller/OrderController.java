package com.ecomerce.ecomerce.controller;

import com.ecomerce.ecomerce.model.Customer;
import com.ecomerce.ecomerce.model.Order;
import com.ecomerce.ecomerce.service.CustomerService;
import com.ecomerce.ecomerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public Order createOrder(@RequestBody Order order){
        return this.orderService.save(order);
    }

    @GetMapping()
    public List<Order> getAllOrder(){
        return this.orderService.findAll();
    }

    @PutMapping("/{orderId}")
    public Order updateItem(@PathVariable int orderId, @RequestBody Order order){

        return this.orderService.update(orderId, order);
    }
}
