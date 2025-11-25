package org.ordermanagement.service;

import org.ordermanagement.dao.CategoryDAO;
import org.ordermanagement.entity.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public Category createCategory(String name, Long parentId) {
        Category parent = null;
        if (parentId != null) parent = categoryDAO.findById(parentId);
        Category c = new Category(name);
        c.setParent(parent);
        categoryDAO.save(c);
        return c;
    }

    public Category getCategory(Long id) {
        return categoryDAO.findById(id);
    }

    public Category getByName(String name) {
        return categoryDAO.findByName(name);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }
}
