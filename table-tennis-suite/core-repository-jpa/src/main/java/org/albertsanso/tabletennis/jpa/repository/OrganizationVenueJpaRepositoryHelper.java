package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationVenue;
import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationVenueJpaRepositoryHelper extends CrudRepository<JpaOrganizationVenue, Long> {
    JpaOrganizationVenue findById(Long id);
    JpaOrganizationVenue findOneByOrganizationAndVenueAndSeason(JpaOrganization jpaOrganization, JpaVenue jpaVenue, String season);

    List<JpaOrganizationVenue> findAll();
    List<JpaOrganizationVenue> findByOrganization(JpaOrganization organization);
}
