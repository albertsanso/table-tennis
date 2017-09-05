package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaOrganizationAlias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationAliasJpaRepositoryHelper extends CrudRepository<JpaOrganizationAlias, Long> {
}
