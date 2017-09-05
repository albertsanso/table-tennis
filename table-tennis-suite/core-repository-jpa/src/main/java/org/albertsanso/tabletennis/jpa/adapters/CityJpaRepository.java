package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.CityToJpaCityMapper;
import org.albertsanso.tabletennis.jpa.mappers.JpaCityToCityMapper;
import org.albertsanso.tabletennis.jpa.model.JpaCity;
import org.albertsanso.tabletennis.jpa.repository.CityJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.City;
import org.albertsanso.tabletennis.port.CityRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CityJpaRepository implements CityRepository {

    private CityToJpaCityMapper cityToJpaCityMapper;
    private JpaCityToCityMapper jpaCityToCityMapper;
    private CityJpaRepositoryHelper cityJpaRepositoryHelper;

    @Inject
    public CityJpaRepository(CityToJpaCityMapper cityToJpaCityMapper, JpaCityToCityMapper jpaCityToCityMapper, CityJpaRepositoryHelper cityJpaRepositoryHelper) {
        this.cityToJpaCityMapper = cityToJpaCityMapper;
        this.jpaCityToCityMapper = jpaCityToCityMapper;
        this.cityJpaRepositoryHelper = cityJpaRepositoryHelper;
    }

    public City save(City city) {
        JpaCity jpaCity = cityToJpaCityMapper.apply(city);
        jpaCity = cityJpaRepositoryHelper.save(jpaCity);
        return jpaCityToCityMapper.apply(jpaCity);
    }

    public void remove(City city) {}

    public City findById(Long id) {
        JpaCity jpaCity = cityJpaRepositoryHelper.findOne(id);
        City city = jpaCityToCityMapper.apply(jpaCity);
        return city;
    }

    @Override
    public City findByCodeName(String codeName) {
        JpaCity jpaCity = cityJpaRepositoryHelper.findByCodeName(codeName);
        return jpaCityToCityMapper.apply(jpaCity);
    }
}
