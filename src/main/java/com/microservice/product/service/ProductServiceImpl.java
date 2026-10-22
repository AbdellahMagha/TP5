package com.microservice.product.service;

import com.microservice.product.dao.IDao;
import com.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IDao<Product> catalogDao;

    @Override
    @Transactional
    public void addProduct(Product record) {
        if (record.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        catalogDao.save(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return catalogDao.findAll();
    }
}
