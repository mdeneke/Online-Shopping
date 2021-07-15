package com.ecomerce.ecomerce.service;

import com.ecomerce.ecomerce.model.Cart;
import com.ecomerce.ecomerce.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public Cart save(Cart cart);

    public List<Cart> findAll();

    public Cart findById(int cartId);

    public Cart update(int cartId, Cart cart);
}
