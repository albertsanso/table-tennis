package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamJpaRepositoryHelper extends CrudRepository<JpaTeam, Long> {
    JpaTeam findByExternalId(String id);
}
