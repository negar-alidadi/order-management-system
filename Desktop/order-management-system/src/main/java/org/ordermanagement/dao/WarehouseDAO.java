package org.ordermanagement.dao;

import org.ordermanagement.entity.Warehouse;
import java.util.List;

public interface WarehouseDAO {
    void save(Warehouse w);
    Warehouse findById(Long id);
    Warehouse findByName(String name);
    List<Warehouse> findAll();
}
