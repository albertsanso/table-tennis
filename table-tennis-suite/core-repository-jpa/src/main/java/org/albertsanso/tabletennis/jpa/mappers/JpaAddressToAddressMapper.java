package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaAddress;
import org.albertsanso.tabletennis.model.Address;
import org.albertsanso.tabletennis.model.City;
import org.albertsanso.tabletennis.model.State;
import org.albertsanso.tabletennis.model.Venue;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaAddressToAddressMapper implements Function<JpaAddress, Address> {

    private JpaStateToStateMapper jpaStateToStateMapper;
    private JpaCityToCityMapper jpaCityToCityMapper;
    //private JpaVenueToVenueMapper jpaVenueToVenueMapper;

    /*
    @Inject
    public JpaAddressToAddressMapper(JpaStateToStateMapper jpaStateToStateMapper, JpaCityToCityMapper jpaCityToCityMapper, @Lazy JpaVenueToVenueMapper jpaVenueToVenueMapper) {
        this.jpaStateToStateMapper = jpaStateToStateMapper;
        this.jpaCityToCityMapper = jpaCityToCityMapper;
        this.jpaVenueToVenueMapper = jpaVenueToVenueMapper;
    }
    */

    @Inject
    public JpaAddressToAddressMapper(JpaStateToStateMapper jpaStateToStateMapper, JpaCityToCityMapper jpaCityToCityMapper) {
        this.jpaStateToStateMapper = jpaStateToStateMapper;
        this.jpaCityToCityMapper = jpaCityToCityMapper;
    }


    @Override
    public Address apply(JpaAddress jpaAddress) {
        if (jpaAddress == null) return null;

        State state = jpaStateToStateMapper.apply(jpaAddress.getState());
        City city = jpaCityToCityMapper.apply(jpaAddress.getCity());
        //Venue venue = null;

        /*
        if (jpaAddress.getVenue() != null) {
            venue = jpaVenueToVenueMapper.apply(jpaAddress.getVenue());
        }
        */

        Address.AddressBuilder builder = new Address.AddressBuilder(
                jpaAddress.getZip(),
                city,
                state);

        Address address = builder
            .withId(jpaAddress.getId())
            .withStreet(jpaAddress.getStreet())
            //.withVenue(venue)
            .create();

        return address;
    }
}
