package com.btsistemas.cursomc;

import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.domain.Product;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import com.btsistemas.cursomc.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        
        Product p1 = new Product(null, "Computador", new BigDecimal("2400.35"));
        Product p2 = new Product(null, "Impressora", new BigDecimal("840.55"));
        Product p3 = new Product(null, "Mouser", new BigDecimal("30.65"));
        
        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));
        
        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));
      
        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
