package org.ordermanagement.dao;

import org.ordermanagement.entity.Order;
import java.util.List;

public interface OrderDAO {
    void save(Order order);
    Order findById(Long id);
    List<Order> findAll();
    void update(Long order);
    void delete(Long id);
}
