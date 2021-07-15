package com.ecomerce.ecomerce.service;

import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.model.Product;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ItemService {

    public Item save(int productId, Item item);

    public List<Item> findAll();

    public Item findById(int itemId);

    public Item update(int itemId, Item item);

    public String UploadImage(int productId, MultipartFile multipartFile);

    public Resource loadFileAsResource(String fileName);
}
