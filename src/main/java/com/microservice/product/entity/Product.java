package com.microservice.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    public Product() {}

    public Product(String prodName, double prodPrice) {
        this.name = prodName;
        this.price = prodPrice;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long identifier) { 
        this.id = identifier; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public void setName(String prodName) { 
        this.name = prodName; 
    }
    
    public double getPrice() { 
        return price; 
    }
    
    public void setPrice(double prodPrice) { 
        this.price = prodPrice; 
    }
}
