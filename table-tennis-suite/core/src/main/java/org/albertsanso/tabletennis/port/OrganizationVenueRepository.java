package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.OrganizationVenue;
import org.albertsanso.tabletennis.model.Venue;

import java.util.List;

public interface OrganizationVenueRepository {
    OrganizationVenue findById(Long id);
    OrganizationVenue findOneByOrganizationAndVenueAndSeason(Organization organization, Venue venue, Season season);

    List<OrganizationVenue> findAll();
    List<OrganizationVenue> findByOrganization(Organization organization);

    OrganizationVenue save(OrganizationVenue organizationVenue);
    void remove(OrganizationVenue organizationVenue);

}
