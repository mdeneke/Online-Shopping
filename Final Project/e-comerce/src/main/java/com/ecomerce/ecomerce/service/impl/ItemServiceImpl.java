package com.ecomerce.ecomerce.service.impl;

import com.ecomerce.ecomerce.ExceptionHandler.FileStorageException;
import com.ecomerce.ecomerce.ExceptionHandler.ResourceNotFoundException;
import com.ecomerce.ecomerce.FileStorageProperties;
import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.repository.ItemRepository;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.service.ItemService;
import com.ecomerce.ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {


    ItemRepository itemRepository;


    ProductRepository productRepository;

    private final Path fileStorageLocation;

    @Autowired
    public ItemServiceImpl(FileStorageProperties fileStorageProperties, ProductRepository productRepository,
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
    public Item save(int productId, Item item) {
        productRepository.findById(productId).map(product -> {
//            product.setItems(List.of(item));
            item.setProduct(product);
            return this.itemRepository.save(item);
        }).orElseThrow(()-> new ResourceNotFoundException("Product " + productId + " not found"));

        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = this.itemRepository.findAll();
        String path = "http://localhost:8080/items/downloadFile/";

        items.stream().forEach(item -> item.setImage(path + item.getImage()));
        return items;
    }

    @Override
    public Item findById(int itemId) {

        Optional<Item> item = this.itemRepository.findById(itemId);
        Item result = item.isPresent() ? item.get() : new Item();

        return result;
    }

    @Override
    public Item update(int ItemId, Item itemRequest) {

        Item item = findById(ItemId);
        if(item != null){
          return  this.itemRepository.save(itemRequest);
        }

        return null;
    }
    @Override
    public String UploadImage(int itemId, MultipartFile multipartFile) {

        Item item = findById(itemId);
        String filPath = this.storeFile(multipartFile);
        System.out.println("StringUtils.cleanPath(multipartFile.getOriginalFilename()) " + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        item.setImage(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        if (item != null) {
            this.itemRepository.save(item);
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
