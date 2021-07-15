package com.ecomerce.ecomerce.service;

import com.ecomerce.ecomerce.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public Address save(int addressId, Address address);

    public List<Address> findAll();

    public Address findById(int addressId);

    public Address update(int itemId, Address addressId);
}
