package com.ecomerce.ecomerce.service;

import com.ecomerce.ecomerce.model.Product;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public Product save(Product product);

    public List<Product> findAll();

    public Iterator<Product> findAllwithItems(int productId);

    public Product findById(int productId);

    public Product update(int productId, Product product);

    public String UploadImage(int productId, MultipartFile multipartFile);

    public Resource loadFileAsResource(String fileName);

}
