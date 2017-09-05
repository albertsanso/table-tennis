package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.jpa.mappers.*;
import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationVenue;
import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.albertsanso.tabletennis.jpa.repository.OrganizationVenueJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.OrganizationVenue;
import org.albertsanso.tabletennis.model.Venue;
import org.albertsanso.tabletennis.port.OrganizationVenueRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class OrganizationVenueJpaRepository implements OrganizationVenueRepository {

    private OrganizationVenueJpaRepositoryHelper organizationVenueJpaRepositoryHelper;
    private OrganizationVenueToJpaOrganizationVenueMapper organizationVenueToJpaOrganizationVenueMapper;
    private JpaOrganizationVenueToOrganizationVenueMapper jpaOrganizationVenueToOrganizationVenueMapper;

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private VenueToJpaVenueMapper venueToJpaVenueMapper;

    @Inject
    public OrganizationVenueJpaRepository(
            OrganizationVenueJpaRepositoryHelper organizationVenueJpaRepositoryHelper,
            OrganizationVenueToJpaOrganizationVenueMapper organizationVenueToJpaOrganizationVenueMapper,
            JpaOrganizationVenueToOrganizationVenueMapper jpaOrganizationVenueToOrganizationVenueMapper,
            OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper,
            VenueToJpaVenueMapper venueToJpaVenueMapper
    ) {
        this.organizationVenueJpaRepositoryHelper = organizationVenueJpaRepositoryHelper;
        this.organizationVenueToJpaOrganizationVenueMapper = organizationVenueToJpaOrganizationVenueMapper;
        this.jpaOrganizationVenueToOrganizationVenueMapper = jpaOrganizationVenueToOrganizationVenueMapper;
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.venueToJpaVenueMapper = venueToJpaVenueMapper;
    }

    public OrganizationVenue save(OrganizationVenue organizationVenue) {
        JpaOrganizationVenue jpaOrganizationVenue = organizationVenueToJpaOrganizationVenueMapper.apply(organizationVenue);
        jpaOrganizationVenue = organizationVenueJpaRepositoryHelper.save(jpaOrganizationVenue);
        return jpaOrganizationVenueToOrganizationVenueMapper.apply(jpaOrganizationVenue);
    }

    public void remove(OrganizationVenue organizationVenue) {
        organizationVenueJpaRepositoryHelper.delete(organizationVenue.getId());
    }

    public OrganizationVenue findById(Long id) {
        JpaOrganizationVenue jpaOrganizationVenue = organizationVenueJpaRepositoryHelper.findOne(id);
        OrganizationVenue organizationVenue = jpaOrganizationVenueToOrganizationVenueMapper.apply(jpaOrganizationVenue);
        return organizationVenue;
    }

    @Override
    public List<OrganizationVenue> findAll() {
        return mapList(organizationVenueJpaRepositoryHelper.findAll());
    }

    @Override
    public OrganizationVenue findOneByOrganizationAndVenueAndSeason(Organization organization, Venue venue, Season season) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organization);
        JpaVenue jpaVenue = venueToJpaVenueMapper.apply(venue);
        JpaOrganizationVenue jpaOrganizationVenue = organizationVenueJpaRepositoryHelper
                .findOneByOrganizationAndVenueAndSeason(jpaOrganization, jpaVenue, season.seasonKey);
        return jpaOrganizationVenueToOrganizationVenueMapper.apply(jpaOrganizationVenue);
    }

    @Override
    public List<OrganizationVenue> findByOrganization(Organization organization) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organization);
        List<JpaOrganizationVenue> jpaOvs = (List) organizationVenueJpaRepositoryHelper.findByOrganization(jpaOrganization);
        return mapList(jpaOvs);
    }

    private List<OrganizationVenue> mapList(List<JpaOrganizationVenue> list) {
        List<OrganizationVenue> ovs = new ArrayList<>();
        for (JpaOrganizationVenue jpaOv : list) {
            OrganizationVenue ov = jpaOrganizationVenueToOrganizationVenueMapper.apply(jpaOv);
            ovs.add(ov);
        }
        return ovs;
    }
}
