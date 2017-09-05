package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaAddress;
import org.albertsanso.tabletennis.jpa.model.JpaCity;
import org.albertsanso.tabletennis.jpa.model.JpaState;
import org.albertsanso.tabletennis.jpa.model.JpaVenue;
import org.albertsanso.tabletennis.model.Address;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class AddressToJpaAddressMapper implements Function<Address, JpaAddress> {

    private CityToJpaCityMapper cityToJpaCityMapper;
    private StateToJpaStateMapper stateToJpaStateMapper;
    //private VenueToJpaVenueMapper venueToJpaVenueMapper;


    @Inject
    public AddressToJpaAddressMapper(CityToJpaCityMapper cityToJpaCityMapper, StateToJpaStateMapper stateToJpaStateMapper/*, @Lazy VenueToJpaVenueMapper venueToJpaVenueMapper*/) {
        this.cityToJpaCityMapper = cityToJpaCityMapper;
        this.stateToJpaStateMapper = stateToJpaStateMapper;
        //this.venueToJpaVenueMapper = venueToJpaVenueMapper;
    }

    @Override
    public JpaAddress apply(Address address) {
        if (address == null) return null;

        JpaCity jpaCity = cityToJpaCityMapper.apply(address.getCity());
        JpaState jpaState = stateToJpaStateMapper.apply(address.getState());
        //JpaVenue jpaVenue = venueToJpaVenueMapper.apply(address.getVenue());

        JpaAddress jpaAddress = new JpaAddress(address.getStreet(), address.getZip());
        jpaAddress.setId(address.getId());
        jpaAddress.setCity(jpaCity);
        jpaAddress.setState(jpaState);
        //jpaAddress.setVenue(jpaVenue);

        return jpaAddress;
    }
}
