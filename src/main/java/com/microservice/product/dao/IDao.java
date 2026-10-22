package com.microservice.product.dao;

import java.util.List;

public interface IDao<T> {
    void save(T entityObj);
    void update(T entityObj);
    void delete(T entityObj);
    T findById(Long identifier);
    List<T> findAll();
}
