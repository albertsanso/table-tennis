package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaCity;
import org.albertsanso.tabletennis.jpa.model.JpaState;
import org.albertsanso.tabletennis.model.City;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class CityToJpaCityMapper implements Function<City, JpaCity> {

    private StateToJpaStateMapper stateToJpaStateMapper;

    @Inject
    public CityToJpaCityMapper(StateToJpaStateMapper stateToJpaStateMapper) {
        this.stateToJpaStateMapper = stateToJpaStateMapper;
    }

    @Override
    public JpaCity apply(City city) {
        JpaState jpaState = stateToJpaStateMapper.apply(city.getState());
        JpaCity jpaCity = new JpaCity(city.getCodeName(), city.getName());
        jpaCity.setId(city.getId());
        jpaCity.setState(jpaState);
        return jpaCity;
    }
}
