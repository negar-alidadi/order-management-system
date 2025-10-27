package org.ordermanagement.service;

import org.ordermanagement.dao.ProductDAO;
import org.ordermanagement.entity.Product;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;
    public ProductService(ProductDAO productDAO) { this.productDAO = productDAO; }

    public void createProduct(String name, Double price) {
        productDAO.save(new Product(name, price));
    }

    public Product getProduct(Long id) {
        return productDAO.findById(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public void updateProduct(Long p) {
        Product product = productDAO.findById(p);
        productDAO.update(product.getId());
    }

    public void deleteProduct(Long id) {
        productDAO.delete(id);
    }
}
