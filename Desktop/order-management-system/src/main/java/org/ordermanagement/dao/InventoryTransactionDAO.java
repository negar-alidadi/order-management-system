package org.ordermanagement.dao;

import org.ordermanagement.entity.InventoryTransaction;

import java.util.List;

public interface InventoryTransactionDAO {
    void save(InventoryTransaction t);
    InventoryTransaction findById(Long id);
    List<InventoryTransaction> findAll();
    List<InventoryTransaction> findByInventory(Long invId);
    List<InventoryTransaction> findByType(InventoryTransaction.Type type);
    List<InventoryTransaction> findByTypeWithInventory();
}
