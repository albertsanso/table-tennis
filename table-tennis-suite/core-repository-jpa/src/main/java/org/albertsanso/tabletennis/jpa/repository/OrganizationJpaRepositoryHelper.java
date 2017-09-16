package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationJpaRepositoryHelper extends CrudRepository<JpaOrganization, Long> {
    JpaOrganization findByCodeName(String codeName);
    JpaOrganization findByExternalId(String id);
}
