package org.ordermanagement.dao;

import org.ordermanagement.entity.OrderItem;
import java.util.List;

public interface OrderItemDAO {
    void save(OrderItem item);
    OrderItem findById(Long id);
    List<OrderItem> findAll();
    void update(Long item);
    void delete(Long id);
}
