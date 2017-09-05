package org.albertsanso.tabletennis.jpa.repository;

import org.albertsanso.tabletennis.jpa.model.JpaAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepositoryHelper extends CrudRepository<JpaAddress, Long> {
    JpaAddress findByStreetAndZip(String street, String zip);
}
