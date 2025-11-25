package org.ordermanagement.service;

import org.ordermanagement.dao.InventoryTransactionDAO;
import org.ordermanagement.entity.InventoryTransaction;
import org.ordermanagement.entity.InventoryTransaction.Type;

import java.util.List;

public class InventoryTransactionService {
    private final InventoryTransactionDAO txDao;

    public InventoryTransactionService(InventoryTransactionDAO txDao) {
        this.txDao = txDao;
    }

    public void saveTransaction(InventoryTransaction tx) {
        txDao.save(tx);
    }

    public InventoryTransaction getById(Long id) {
        return txDao.findById(id);
    }

    public List<InventoryTransaction> getAll() {
        return txDao.findAll();
    }

    public List<InventoryTransaction> getByInventory(Long invId) {
        return txDao.findByInventory(invId);
    }

    public List<InventoryTransaction> getByType(Type type) {
        return txDao.findByType(type);
    }
}
