package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

@Named
@Repository
public interface StateJpaRepositoryHelper extends CrudRepository<JpaState, Long> {
    JpaState findByCodeName(String codeName);
}
