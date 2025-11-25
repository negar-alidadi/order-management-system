package org.ordermanagement.dao;

import org.ordermanagement.entity.Category;
import java.util.List;

public interface CategoryDAO {
    void save(Category c);
    Category findById(Long id);
    Category findByName(String name);
    List<Category> findAll();
}
