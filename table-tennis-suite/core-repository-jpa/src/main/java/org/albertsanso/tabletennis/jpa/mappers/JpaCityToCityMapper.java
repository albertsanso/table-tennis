package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaCity;
import org.albertsanso.tabletennis.model.City;
import org.albertsanso.tabletennis.model.State;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaCityToCityMapper implements Function<JpaCity, City> {

    private JpaStateToStateMapper jpaStateToStateMapper;

    @Inject
    public JpaCityToCityMapper(JpaStateToStateMapper jpaStateToStateMapper) {
        this.jpaStateToStateMapper = jpaStateToStateMapper;
    }

    @Override
    public City apply(JpaCity jpaCity) {
        if (jpaCity == null) return null;

        State state = jpaStateToStateMapper.apply(jpaCity.getState());

        City.CityBuilder builder = new City.CityBuilder(jpaCity.getCodeName(), jpaCity.getName(), state);
        City city = builder
                .withId(jpaCity.getId())
                .create();

        return city;
    }
}
