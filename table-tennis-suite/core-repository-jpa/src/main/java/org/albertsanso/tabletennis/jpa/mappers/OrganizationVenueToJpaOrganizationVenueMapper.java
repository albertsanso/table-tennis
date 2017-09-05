package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationVenue;
import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.albertsanso.tabletennis.model.OrganizationVenue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class OrganizationVenueToJpaOrganizationVenueMapper implements Function<OrganizationVenue, JpaOrganizationVenue> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private VenueToJpaVenueMapper venueToJpaVenueMapper;

    @Inject
    public OrganizationVenueToJpaOrganizationVenueMapper(OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper, VenueToJpaVenueMapper venueToJpaVenueMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.venueToJpaVenueMapper = venueToJpaVenueMapper;
    }

    @Override
    public JpaOrganizationVenue apply(OrganizationVenue organizationVenue) {
        JpaVenue jpaVenue = venueToJpaVenueMapper.apply(organizationVenue.getVenue());
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organizationVenue.getOrganization());

        JpaOrganizationVenue jpaOrganizationVenue = new JpaOrganizationVenue(jpaOrganization, jpaVenue, organizationVenue.getSeason());
        return jpaOrganizationVenue;
    }
}
