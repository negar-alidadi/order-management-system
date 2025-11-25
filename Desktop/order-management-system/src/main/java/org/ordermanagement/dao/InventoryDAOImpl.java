package org.ordermanagement.dao;

import org.ordermanagement.entity.Inventory;
import jakarta.persistence.*;
import java.util.Optional;

public class InventoryDAOImpl implements InventoryDAO {
    private final EntityManagerFactory emf;
    public InventoryDAOImpl(EntityManagerFactory emf) { this.emf = emf; }

    @Override
    public Optional<Inventory> findByProductAndWarehouse(Long productId, Long warehouseId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Inventory> q = em.createNamedQuery("Inventory.findByProductAndWarehouse", Inventory.class);
            q.setParameter("pid", productId);
            q.setParameter("wid", warehouseId);

            Inventory inv = q.getResultStream().findFirst().orElse(null);
            return Optional.ofNullable(inv);

        } finally {
            em.close();
        }
    }

    @Override
    public Inventory save(Inventory inventory) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(inventory);
        em.getTransaction().commit();
        em.close();
        return inventory;
    }

    @Override
    public Inventory findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try { return em.find(Inventory.class, id); } finally { em.close(); }
    }

    @Override
    public void update(Inventory inventory) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(inventory);
        em.getTransaction().commit();
        em.close();
    }
}
