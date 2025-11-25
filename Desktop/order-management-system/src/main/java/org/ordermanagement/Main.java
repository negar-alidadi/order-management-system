package org.ordermanagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.ordermanagement.dao.*;
import org.ordermanagement.entity.*;
import org.ordermanagement.service.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");

        CustomerDAO customerDAO = new CustomerDAOImpl(emf);
        ProductDAO productDAO = new ProductDAOImpl(emf);
        OrderDAO orderDAO = new OrderDAOImpl(emf);
        InventoryDAO inventoryDAO = new InventoryDAOImpl(emf);
        InventoryTransactionDAO txDAO = new InventoryTransactionDAOImpl(emf);
        WarehouseDAO warehouseDAO = new WarehouseDAOImpl(emf);
        CategoryDAO categoryDAO = new CategoryDAOImpl(emf);

        CustomerService customerService = new CustomerService(customerDAO);
        ProductService productService = new ProductService(productDAO);
        OrderService orderService = new OrderService(orderDAO, inventoryDAO);
        WarehouseService warehouseService = new WarehouseService(warehouseDAO);
        CategoryService categoryService = new CategoryService(categoryDAO);
        InventoryService inventoryService = new InventoryService(inventoryDAO);
        InventoryTransactionService txService = new InventoryTransactionService(txDAO);
        SalesReportService reportService = new SalesReportService(emf,txDAO);

        customerService.createCustomer("Negar Alidadi", "negar@example.com", "0912000000");
        productService.createProduct("Laptop", 2500d);
        productService.createProduct("Mouse", 50d);

        Customer c = customerService.getAllCustomers().get(0);
        Product p1 = productService.getAllProducts().get(0);
        Product p2 = productService.getAllProducts().get(1);

        Warehouse w = new Warehouse("Main Warehouse", "Tehran");
        warehouseService.createWarehouse(w);
        Warehouse savedWarehouse = warehouseService.getAllWarehouses().get(0);

        Inventory inv1 = new Inventory(p1, savedWarehouse, 10);
        Inventory inv2 = new Inventory(p2, savedWarehouse, 50);
        inventoryService.createInventory(inv1);
        inventoryService.createInventory(inv2);

        Order order = new Order(c, LocalDate.now(), 0d);
        order.addItem(new OrderItem(p1, 1, 2500d));
        order.addItem(new OrderItem(p2, 1, 50d));
        double total = order.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        order.setTotalAmount(total);

        orderService.createOrder(order, savedWarehouse.getId());

        Inventory i1 = inventoryService.findByProductAndWarehouse(p1.getId(), savedWarehouse.getId()).orElse(null);
        Inventory i2 = inventoryService.findByProductAndWarehouse(p2.getId(), savedWarehouse.getId()).orElse(null);
        if (i1 != null) txService.saveTransaction(new InventoryTransaction(i1, InventoryTransaction.Type.SALE, 1, order.getId(), "Sold 1 laptop"));
        if (i2 != null) txService.saveTransaction(new InventoryTransaction(i2, InventoryTransaction.Type.SALE, 1, order.getId(), "Sold 1 mouse"));

        System.out.println("=== Customers ===");
        customerService.getAllCustomers().forEach(System.out::println);

        System.out.println("=== Products ===");
        productService.getAllProducts().forEach(System.out::println);

        System.out.println("=== Orders ===");
        orderService.getAllOrders().forEach(o -> {
            System.out.println(o);
            o.getItems().forEach(System.out::println);
        });

        System.out.println("=== Sales Summary ===");
        reportService.printSalesReport();


        emf.close();
    }
}
