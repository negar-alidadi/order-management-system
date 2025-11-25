package org.ordermanagement.service;

import org.ordermanagement.dao.InventoryDAO;
import org.ordermanagement.entity.Inventory;

import java.util.Optional;

public class InventoryService {
    private final InventoryDAO inventoryDAO;

    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public Inventory createInventory(Inventory inv) {
        return inventoryDAO.save(inv);
    }

    public Optional<Inventory> findByProductAndWarehouse(Long productId, Long warehouseId) {
        return inventoryDAO.findByProductAndWarehouse(productId, warehouseId);
    }

    public Inventory findById(Long id) {
        return inventoryDAO.findById(id);
    }

}
