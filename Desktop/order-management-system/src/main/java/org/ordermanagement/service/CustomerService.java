package org.ordermanagement.service;

import org.ordermanagement.dao.CustomerDAO;
import org.ordermanagement.dao.CustomerDAOImpl;
import org.ordermanagement.entity.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void createCustomer(String name, String email, String phone) {
        customerDAO.save(new Customer(name, email, phone));
    }

    public Customer getCustomer(Long id) {
        return customerDAO.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public void updateCustomer(Customer c) {
        customerDAO.update(c);
    }

    public void deleteCustomer(Long id) {
        customerDAO.delete(id);
    }
}
