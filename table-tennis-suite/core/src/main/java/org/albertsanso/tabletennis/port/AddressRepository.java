package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Address;

public interface AddressRepository {
    Address save(Address address);
    void remove(Address address);
    Address findById(Long id);
    Address findByStreetAndZip(String street, String zip);
}
