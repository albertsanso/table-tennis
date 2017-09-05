package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.albertsanso.tabletennis.model.Address;
import org.albertsanso.tabletennis.model.Venue;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaVenueToVenueMapper implements Function<JpaVenue, Venue> {

    private JpaAddressToAddressMapper jpaAddressToAddressMapper;

    @Inject
    public JpaVenueToVenueMapper(JpaAddressToAddressMapper jpaAddressToAddressMapper) {
        this.jpaAddressToAddressMapper = jpaAddressToAddressMapper;
    }

    @Override
    public Venue apply(JpaVenue jpaVenue) {
        if (jpaVenue == null) return null;

        Address address = jpaAddressToAddressMapper.apply(jpaVenue.getAddress());

        Venue.VenueBuilder venueBuilder = (new Venue.VenueBuilder(jpaVenue.getCodeName(), jpaVenue.getName()));
        Venue venue = venueBuilder
                .withId(jpaVenue.getId())
                .withAddress(address)
                .create();
        return venue;
    }
}
