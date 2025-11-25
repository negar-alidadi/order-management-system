package org.ordermanagement.dao;

import jakarta.persistence.*;
import org.ordermanagement.entity.Category;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private final EntityManagerFactory emf;

    public CategoryDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Category c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Category findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Category c = em.find(Category.class, id);
        em.close();
        return c;
    }

    @Override
    public Category findByName(String name) {
        EntityManager em = emf.createEntityManager();
        Category res = em.createNamedQuery("Category.findByName", Category.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst().orElse(null);
        em.close();
        return res;
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Category> list = em.createNamedQuery("Category.findAll", Category.class)
                .getResultList();
        em.close();
        return list;
    }
}
