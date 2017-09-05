package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.City;

public interface CityRepository {
    City save(City city);
    void remove(City city);
    City findById(Long id);
    City findByCodeName(String codeName);
}
