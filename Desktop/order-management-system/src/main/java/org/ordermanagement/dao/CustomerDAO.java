package org.ordermanagement.dao;

import org.ordermanagement.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    void save(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
    void update(Customer customer);
    void delete(Long id);
}
