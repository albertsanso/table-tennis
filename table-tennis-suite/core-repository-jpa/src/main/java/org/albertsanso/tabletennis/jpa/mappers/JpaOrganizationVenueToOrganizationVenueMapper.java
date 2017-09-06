package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationVenue;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.OrganizationVenue;
import org.albertsanso.tabletennis.model.Venue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaOrganizationVenueToOrganizationVenueMapper implements Function<JpaOrganizationVenue, OrganizationVenue> {

    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;
    private JpaVenueToVenueMapper jpaVenueToVenueMapper;

    @Inject
    public JpaOrganizationVenueToOrganizationVenueMapper(JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper, JpaVenueToVenueMapper jpaVenueToVenueMapper) {
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
        this.jpaVenueToVenueMapper = jpaVenueToVenueMapper;
    }

    @Override
    public OrganizationVenue apply(JpaOrganizationVenue jpaOrganizationVenue) {
        if (jpaOrganizationVenue == null) return null;

        Organization organization = jpaOrganizationToOrganizationMapper.apply(jpaOrganizationVenue.getOrganization());
        Venue venue = jpaVenueToVenueMapper.apply(jpaOrganizationVenue.getVenue());

        Season season = Season.getByKey(jpaOrganizationVenue.getSeason());

        OrganizationVenue.OrganizationVenueBuilder builder = new OrganizationVenue.OrganizationVenueBuilder(
                organization,
                venue,
                season
        );

        OrganizationVenue ov = builder
                .withId(jpaOrganizationVenue.getId())
                .create();
        return ov;
    }
}
