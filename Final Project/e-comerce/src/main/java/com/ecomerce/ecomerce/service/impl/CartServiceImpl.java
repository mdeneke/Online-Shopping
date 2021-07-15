package com.ecomerce.ecomerce.service.impl;

import com.ecomerce.ecomerce.model.Cart;
import com.ecomerce.ecomerce.model.Customer;
import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.repository.CartRepository;
import com.ecomerce.ecomerce.repository.CustomerRepository;
import com.ecomerce.ecomerce.repository.ItemRepository;
import com.ecomerce.ecomerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Cart save(Cart cart) {
        Customer customer = this.customerRepository.findById(cart.getCustomerId()).get();

        Item item = this.itemRepository.findById(cart.getItemId()).get();

        cart.setCustomer(customer);
        cart.setItem(item);
        return this.cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(int cartId) {
        return null;
    }

    @Override
    public Cart update(int cartId, Cart cart) {
        return null;
    }
}
