package org.ordermanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ordermanagement.entity.Product;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final EntityManagerFactory emf;
    public ProductDAOImpl(EntityManagerFactory emf) { this.emf = emf; }

    @Override
    public void save(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Product findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Product p = em.find(Product.class, id);
        em.close();
        return p;
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Product> list = em.createNamedQuery("Product.findAll").getResultList();
        em.close();
        return list;
    }

    @Override
    public void update(Long product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product p = em.find(Product.class, id);
        if (p != null) em.remove(p);
        em.getTransaction().commit();
        em.close();
    }
}
