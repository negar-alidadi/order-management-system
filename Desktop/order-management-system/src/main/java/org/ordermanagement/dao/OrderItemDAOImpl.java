package org.ordermanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ordermanagement.entity.OrderItem;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    private final EntityManagerFactory emf;

    public OrderItemDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(OrderItem item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public OrderItem findById(Long id) {
        EntityManager em = emf.createEntityManager();
        OrderItem i = em.find(OrderItem.class, id);
        em.close();
        return i;
    }

    @Override
    public List<OrderItem> findAll() {
        EntityManager em = emf.createEntityManager();
        List<OrderItem> list = em.createNamedQuery("OrderItem.findAll").getResultList();
        em.close();
        return list;
    }

    @Override
    public void update(Long item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        OrderItem i = em.find(OrderItem.class, id);
        if (i != null) em.remove(i);
        em.getTransaction().commit();
        em.close();
    }
}
