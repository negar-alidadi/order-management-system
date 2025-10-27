package org.ordermanagement.service;

import org.ordermanagement.dao.OrderDAO;
import org.ordermanagement.entity.Order;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void createOrder(Order order) {
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
