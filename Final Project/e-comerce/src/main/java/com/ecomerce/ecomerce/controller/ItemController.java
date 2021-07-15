package com.ecomerce.ecomerce.controller;

import com.ecomerce.ecomerce.model.Item;
import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.service.ItemService;
import com.ecomerce.ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/{productId}")
    public Item createItem(@PathVariable int productId, @RequestBody Item item){
        return this.itemService.save(productId, item);
    }

    @PostMapping("/upload/{id}")
    public String createProductImage(@PathVariable int id, @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {

        this.itemService.UploadImage(id,multipartFile);

        return "success ";
    }

    @GetMapping()
    public List<Item> getAllItems(){
        return this.itemService.findAll();
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable int itemId){

        return this.itemService.findById(itemId);
    }

    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable int itemId, @RequestBody Item item){

        return this.itemService.update(itemId, item);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = itemService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
