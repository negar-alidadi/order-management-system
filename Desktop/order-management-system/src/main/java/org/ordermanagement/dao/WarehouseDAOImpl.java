package org.ordermanagement.dao;

import jakarta.persistence.*;
import org.ordermanagement.entity.Warehouse;

import java.util.List;

public class WarehouseDAOImpl implements WarehouseDAO {

    private final EntityManagerFactory emf;

    public WarehouseDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Warehouse w) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(w);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Warehouse findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Warehouse w = em.find(Warehouse.class, id);
        em.close();
        return w;
    }

    @Override
    public Warehouse findByName(String name) {
        EntityManager em = emf.createEntityManager();
        Warehouse w = em.createNamedQuery("Warehouse.findByName", Warehouse.class)
                .setParameter("name", name)
                .getResultStream().findFirst().orElse(null);
        em.close();
        return w;
    }

    @Override
    public List<Warehouse> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Warehouse> list = em.createNamedQuery("Warehouse.findAll", Warehouse.class)
                .getResultList();
        em.close();
        return list;
    }
}
