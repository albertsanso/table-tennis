package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaCity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityJpaRepositoryHelper extends CrudRepository<JpaCity, Long> {
    JpaCity findByCodeName(String codeName);
}
