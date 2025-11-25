package org.ordermanagement.dao;

import org.ordermanagement.entity.Inventory;
import java.util.Optional;

public interface InventoryDAO {
    Optional<Inventory> findByProductAndWarehouse(Long productId, Long warehouseId);
    Inventory save(Inventory inventory);
    Inventory findById(Long id);
    void update(Inventory inventory);
}
