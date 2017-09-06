package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionJpaRepositoryHelper extends CrudRepository<JpaCompetition, Long> {
}
