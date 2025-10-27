package org.ordermanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ordermanagement.entity.Order;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final EntityManagerFactory emf;

    public OrderDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Order order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Order findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Order o = em.find(Order.class, id);
        em.close();
        return o;
    }

    @Override
    public List<Order> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Order> list = em.createNamedQuery("Order.findAllWithItems").getResultList();
        em.close();
        return list;
    }

    @Override
    public void update(Long order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order o = em.find(Order.class, id);
        if (o != null) em.remove(o);
        em.getTransaction().commit();
        em.close();
    }
}
