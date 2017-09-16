package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaPlayer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerJpaRepositoryHelper extends CrudRepository<JpaPlayer, Long>{
    JpaPlayer findByExternalId(String id);
}
