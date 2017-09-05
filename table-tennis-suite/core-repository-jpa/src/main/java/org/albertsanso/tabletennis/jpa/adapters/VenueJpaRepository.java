package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.JpaVenueToVenueMapper;
import org.albertsanso.tabletennis.jpa.mappers.VenueToJpaVenueMapper;
import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.albertsanso.tabletennis.jpa.repository.VenueJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Venue;
import org.albertsanso.tabletennis.port.VenueRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
public class VenueJpaRepository implements VenueRepository {

    private VenueToJpaVenueMapper venueToJpaVenueMapper;
    private JpaVenueToVenueMapper jpaVenueToVenueMapper;
    private VenueJpaRepositoryHelper venueJpaRepositoryHelper;

    @Inject
    public VenueJpaRepository(VenueToJpaVenueMapper venueToJpaVenueMapper, JpaVenueToVenueMapper jpaVenueToVenueMapper, VenueJpaRepositoryHelper venueJpaRepositoryHelper) {
        this.venueToJpaVenueMapper = venueToJpaVenueMapper;
        this.jpaVenueToVenueMapper = jpaVenueToVenueMapper;
        this.venueJpaRepositoryHelper = venueJpaRepositoryHelper;
    }

    public Venue save(Venue venue) {
        JpaVenue jpaVenue = venueToJpaVenueMapper.apply(venue);
        jpaVenue = venueJpaRepositoryHelper.save(jpaVenue);
        return jpaVenueToVenueMapper.apply(jpaVenue);
    }

    public void remove(Venue venue) {}

    public Venue findById(Long id) {
        JpaVenue jpaVenue = venueJpaRepositoryHelper.findOne(id);
        Venue venue = jpaVenueToVenueMapper.apply(jpaVenue);
        return venue;
    }

    @Override
    public List<Venue> findAll() {
        List<Venue> venueList = new ArrayList<Venue>();
        List<JpaVenue> jpaVenueList = venueJpaRepositoryHelper.findAll();
        for (JpaVenue jpaVenue : jpaVenueList) {
            Venue venue = jpaVenueToVenueMapper.apply(jpaVenue);
            venueList.add(venue);
        }
        return venueList;
    }
}
