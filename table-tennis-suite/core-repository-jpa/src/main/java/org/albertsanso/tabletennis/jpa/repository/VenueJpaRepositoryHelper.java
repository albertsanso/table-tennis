package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueJpaRepositoryHelper extends CrudRepository<JpaVenue, Long> {
    List<JpaVenue> findAll();
}
