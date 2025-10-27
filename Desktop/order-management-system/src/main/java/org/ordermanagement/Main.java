package org.ordermanagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.ordermanagement.dao.*;
import org.ordermanagement.entity.Customer;
import org.ordermanagement.entity.Order;
import org.ordermanagement.entity.OrderItem;
import org.ordermanagement.entity.Product;
import org.ordermanagement.service.CustomerService;
import org.ordermanagement.service.OrderService;
import org.ordermanagement.service.ProductService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");


        CustomerDAO customerDAO = new CustomerDAOImpl(emf);
        ProductDAO productDAO = new ProductDAOImpl(emf);
        OrderDAO orderDAO = new OrderDAOImpl(emf);

        CustomerService customerService = new CustomerService(customerDAO);
        ProductService productService = new ProductService(productDAO);
        OrderService orderService = new OrderService(orderDAO);

        customerService.createCustomer("Negar Alidadi", "negar@example.com", "0912000000");
        productService.createProduct("Laptop", 2500d);
        productService.createProduct("Mouse", 50d);

        Customer c = customerService.getAllCustomers().get(0);
        Product p1 = productService.getAllProducts().get(0);
        Product p2 = productService.getAllProducts().get(1);

        Order order = new Order(c, LocalDate.now(), 0d);

        order.addItem(new OrderItem(p1, 1, 2500d));
        order.addItem(new OrderItem(p2, 1, 50d));

        double total = order.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        order.setTotalAmount(total);

        orderService.createOrder(order);

        System.out.println("=== Customers ===");
        customerService.getAllCustomers().forEach(System.out::println);

        System.out.println("=== Products ===");
        productService.getAllProducts().forEach(System.out::println);

        System.out.println("=== Orders ===");
        orderService.getAllOrders().forEach(o -> {
            System.out.println(o);
            o.getItems().forEach(System.out::println);
        });

        emf.close();
    }
}
