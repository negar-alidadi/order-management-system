package org.ordermanagement.service;

import org.ordermanagement.dao.InventoryDAO;
import org.ordermanagement.dao.OrderDAO;
import org.ordermanagement.entity.Inventory;
import org.ordermanagement.entity.Order;
import org.ordermanagement.entity.OrderItem;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;
    private final InventoryDAO inventoryDAO;

    public OrderService(OrderDAO orderDAO, InventoryDAO inventoryDAO) {
        this.orderDAO = orderDAO;
        this.inventoryDAO = inventoryDAO;
    }

    public void createOrder(Order order, Long warehouseId) {

        for (OrderItem item : order.getItems()) {

            Long productId = item.getProduct().getId();

            Inventory inv = inventoryDAO
                    .findByProductAndWarehouse(productId, warehouseId)
                    .orElseThrow(() ->
                            new IllegalStateException("Inventory not found for product=" + productId)
                    );

            if (inv.getQuantity() < item.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for product=" + productId);
            }

            inv.setQuantity(inv.getQuantity() - item.getQuantity());

            inventoryDAO.update(inv);
        }
        orderDAO.save(order);
    }

    public Order getOrder(Long id) {
        return orderDAO.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public void updateOrder(Long order) {
        Order o = orderDAO.findById(order);
        orderDAO.update(o.getId());
    }

    public void deleteOrder(Long id) {
        orderDAO.delete(id);
    }
}
