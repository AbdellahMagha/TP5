package com.microservice.product.dao;

import com.microservice.product.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDaoImpl implements IDao<Product> {

    @Autowired
    private SessionFactory sessionFactoryInstance;

    @Override
    public void save(Product item) {
        sessionFactoryInstance.getCurrentSession().save(item);
    }

    @Override
    public void update(Product item) {
        sessionFactoryInstance.getCurrentSession().update(item);
    }

    @Override
    public void delete(Product item) {
        sessionFactoryInstance.getCurrentSession().delete(item);
    }

    @Override
    public Product findById(Long identifier) {
        return sessionFactoryInstance.getCurrentSession().get(Product.class, identifier);
    }

    @Override
    public List<Product> findAll() {
        return sessionFactoryInstance.getCurrentSession().createQuery("FROM Product", Product.class).list();
    }
}
