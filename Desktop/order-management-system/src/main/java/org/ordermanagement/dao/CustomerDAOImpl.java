package org.ordermanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ordermanagement.entity.Customer;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private final EntityManagerFactory emf;

    public CustomerDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Customer findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Customer c = em.find(Customer.class, id);
        em.close();
        return c;
    }

    @Override
    public List<Customer> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Customer> list = em.createQuery("FROM Customer", Customer.class).getResultList();
        em.close();
        return list;
    }

    @Override
    public void update(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer c = em.find(Customer.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit();
        em.close();
    }
}
