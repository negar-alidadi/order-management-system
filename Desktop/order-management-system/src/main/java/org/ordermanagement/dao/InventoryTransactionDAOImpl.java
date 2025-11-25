package org.ordermanagement.dao;

import jakarta.persistence.*;
import org.ordermanagement.entity.InventoryTransaction;

import java.util.List;

public class InventoryTransactionDAOImpl implements InventoryTransactionDAO {

    private final EntityManagerFactory emf;

    public InventoryTransactionDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(InventoryTransaction t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public InventoryTransaction findById(Long id) {
        EntityManager em = emf.createEntityManager();
        InventoryTransaction t = em.find(InventoryTransaction.class, id);
        em.close();
        return t;
    }

    @Override
    public List<InventoryTransaction> findAll() {
        EntityManager em = emf.createEntityManager();
        List<InventoryTransaction> list =
                em.createNamedQuery("InventoryTransaction.findAll", InventoryTransaction.class).getResultList();
        em.close();
        return list;
    }

    @Override
    public List<InventoryTransaction> findByInventory(Long invId) {
        EntityManager em = emf.createEntityManager();
        List<InventoryTransaction> list =
                em.createNamedQuery("InventoryTransaction.findByInventory", InventoryTransaction.class)
                        .setParameter("invId", invId)
                        .getResultList();
        em.close();
        return list;
    }

    @Override
    public List<InventoryTransaction> findByType(InventoryTransaction.Type type) {
        EntityManager em = emf.createEntityManager();
        List<InventoryTransaction> list =
                em.createNamedQuery("InventoryTransaction.findByType", InventoryTransaction.class)
                        .setParameter("type", type)
                        .getResultList();
        em.close();
        return list;
    }

    @Override
    public List<InventoryTransaction> findByTypeWithInventory() {
        EntityManager entityManager=emf.createEntityManager();
        return entityManager.createNamedQuery("InventoryTransaction.findByTypeWithInventoryAndProduct")
                .setParameter("type",InventoryTransaction.Type.SALE)
                .getResultList();
    }
}
