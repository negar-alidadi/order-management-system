package org.ordermanagement.service;

import org.ordermanagement.dao.OrderItemDAO;
import org.ordermanagement.entity.OrderItem;

import java.util.List;

public class OrderItemService {
    private final OrderItemDAO orderItemDAO;

    public OrderItemService(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public void createOrderItem(OrderItem item) {
        orderItemDAO.save(item);
    }

    public OrderItem getOrderItem(Long id) {
        return orderItemDAO.findById(id);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemDAO.findAll();
    }

    public void updateOrderItem(Long item) {
        OrderItem orderItem = orderItemDAO.findById(item);
        orderItemDAO.update(orderItem.getId());
    }

    public void deleteOrderItem(Long id) {
        orderItemDAO.delete(id);
    }
}
