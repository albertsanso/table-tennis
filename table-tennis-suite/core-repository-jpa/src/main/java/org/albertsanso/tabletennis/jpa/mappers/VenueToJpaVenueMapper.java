package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaAddress;
import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.albertsanso.tabletennis.model.Venue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class VenueToJpaVenueMapper implements Function<Venue, JpaVenue> {

    private AddressToJpaAddressMapper addressToJpaAddressMapper;

    @Inject
    public VenueToJpaVenueMapper(AddressToJpaAddressMapper addressToJpaAddressMapper) {
        this.addressToJpaAddressMapper = addressToJpaAddressMapper;
    }

    @Override
    public JpaVenue apply(Venue venue) {

        JpaAddress jpaAddress = addressToJpaAddressMapper.apply(venue.getAddress());
        JpaVenue jpaVenue = new JpaVenue(venue.getCodeName(), venue.getName());
        jpaVenue.setId(venue.getId());
        jpaVenue.setAddress(jpaAddress);
        return jpaVenue;
    }
}
