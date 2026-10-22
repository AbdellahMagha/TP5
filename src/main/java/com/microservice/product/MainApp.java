package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService catalogService = springContext.getBean(ProductService.class);

        Product article = new Product("Microservice Gateway", 149.99);
        catalogService.addProduct(article);

        System.out.println("Produit ajouté avec succès ! Total en base : " + catalogService.getAllProducts().size());

        springContext.close();
    }
}
