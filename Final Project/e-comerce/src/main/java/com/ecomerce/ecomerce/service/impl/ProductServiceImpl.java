package com.ecomerce.ecomerce.service.impl;

import com.ecomerce.ecomerce.ExceptionHandler.FileStorageException;
import com.ecomerce.ecomerce.FileStorageProperties;
import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.repository.ItemRepository;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ItemRepository itemRepository;

    private final Path fileStorageLocation;

    @Autowired
    public ProductServiceImpl(FileStorageProperties fileStorageProperties, ProductRepository productRepository,
                              ItemRepository itemRepository) {
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;


        try {
            this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                    .toAbsolutePath().normalize();
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = this.productRepository.findAll();
        String path = "http://localhost:8080/products/downloadFile/";

        products.stream().forEach(product -> product.setImage(path + product.getImage()));

        return products;
    }

    @Override
    public Iterator<Product> findAllwithItems(int productId) {

        List<Product> products = this.productRepository.findAll();
        Iterator<Product> productIterator = products.iterator();
        while (productIterator.hasNext()) {
            List<Item> items = this.itemRepository.findByProductId(productIterator.next().getId());
//            productIterator.next().setItems(items);
        }
        return productIterator;
    }

    @Override
    public Product findById(int productId) {

        Optional<Product> product = this.productRepository.findById(productId);
        Product result = product.isPresent() ? product.get() : null;

        return result;
    }

    @Override
    public Product update(int productId, Product productRequest) {

        Product product = findById(productId);
        if (product != null) {
            return this.productRepository.save(productRequest);
        }

        return null;

    }

    @Override
    public String UploadImage(int productId, MultipartFile multipartFile) {

        Product product = findById(productId);
        String filPath = this.storeFile(multipartFile);
        System.out.println("StringUtils.cleanPath(multipartFile.getOriginalFilename()) " + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        product.setImage(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        if (product != null) {
            this.productRepository.save(product);
//            System.out.println("product.getLogoImagePath()" + product.getLogoImagePath());
            return filPath;
        }

        return filPath;

    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String f = "";
        try {
            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
//            f= resource.toString();
            return fileName;
        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
        return fileName;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
//                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
//            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
        return null;
    }
}
