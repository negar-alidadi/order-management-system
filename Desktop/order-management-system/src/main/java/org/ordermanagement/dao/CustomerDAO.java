package org.ordermanagement.dao;

import org.ordermanagement.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    Customer save(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
    void update(Long customer);
    void delete(Long id);
}
