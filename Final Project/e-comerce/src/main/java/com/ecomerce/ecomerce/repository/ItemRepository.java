package com.ecomerce.ecomerce.repository;

import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    public List<Item> findByProductId(int productId);
}
