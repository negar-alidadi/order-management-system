package org.ordermanagement.service;

import org.ordermanagement.dao.WarehouseDAO;
import org.ordermanagement.entity.Warehouse;

import java.util.List;

public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public void createWarehouse(Warehouse warehouse) {
        warehouseDAO.save(warehouse);
    }

    public Warehouse getWarehouse(Long id) {
        return warehouseDAO.findById(id);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseDAO.findAll();
    }
}
