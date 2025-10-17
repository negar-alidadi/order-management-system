package org.ordermanagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.ordermanagement.dao.CustomerDAO;
import org.ordermanagement.dao.CustomerDAOImpl;
import org.ordermanagement.entity.Customer;
import org.ordermanagement.service.CustomerService;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
        CustomerDAO dao = new CustomerDAOImpl(emf);
        CustomerService service = new CustomerService(dao);
        service.createCustomer("Negar Alidadi", "negar@example.com", "0912000000");

        System.out.println("All customers:");
        service.getAllCustomers().forEach(System.out::println);

        Customer c = service.getCustomer(1L);
        if (c != null) {
            c.setPhone("0935123456");
            service.updateCustomer(c);
        }

        System.out.println("After update:");
        service.getAllCustomers().forEach(System.out::println);

        service.deleteCustomer(1L);
        System.out.println("After delete:");
        service.getAllCustomers().forEach(System.out::println);
        emf.close();
    }
}