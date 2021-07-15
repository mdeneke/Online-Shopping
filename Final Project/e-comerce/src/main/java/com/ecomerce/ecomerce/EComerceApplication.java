package com.ecomerce.ecomerce;

import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class EComerceApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(EComerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p2 = new Product();
		p2.setId(1);
		p2.setName("Club");
		this.productRepository.save(p2);
		Product p3 = new Product();
		p3.setId(2);
		p3.setName("Player");
		this.productRepository.save(p3);
	}
}
