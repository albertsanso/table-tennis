package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepositoryHelper extends CrudRepository<JpaCategory, Long> {
}
