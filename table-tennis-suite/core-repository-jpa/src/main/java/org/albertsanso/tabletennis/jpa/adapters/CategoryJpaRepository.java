package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.model.Category;
import org.albertsanso.tabletennis.port.CategoryRepository;

import javax.inject.Named;

@Named
public class CategoryJpaRepository implements CategoryRepository {
    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void remove(Category category) {

    }

    @Override
    public Category findById(Long id) {
        return null;
    }
}
