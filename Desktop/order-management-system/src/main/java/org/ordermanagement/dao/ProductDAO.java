package org.ordermanagement.dao;

import org.ordermanagement.entity.Product;

import java.util.List;

public interface ProductDAO {
    void save(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void update(Long product);
    void delete(Long id);
}
