package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Category;

public interface CategoryRepository {
    Category save(Category category);
    void remove(Category category);
    Category findById(Long id);
}
