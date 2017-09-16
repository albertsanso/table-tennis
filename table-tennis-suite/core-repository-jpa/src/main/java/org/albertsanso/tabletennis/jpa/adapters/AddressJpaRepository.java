package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.AddressToJpaAddressMapper;
import org.albertsanso.tabletennis.jpa.mappers.JpaAddressToAddressMapper;
import org.albertsanso.tabletennis.jpa.model.JpaAddress;
import org.albertsanso.tabletennis.jpa.repository.AddressJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Address;
import org.albertsanso.tabletennis.port.AddressRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AddressJpaRepository implements AddressRepository {

    private AddressToJpaAddressMapper addressToJpaAddressMapper;
    private JpaAddressToAddressMapper jpaAddressToAddressMapper;
    private AddressJpaRepositoryHelper addressJpaRepositoryHelper;

    @Inject
    public AddressJpaRepository(AddressToJpaAddressMapper addressToJpaAddressMapper, JpaAddressToAddressMapper jpaAddressToAddressMapper, AddressJpaRepositoryHelper addressJpaRepositoryHelper) {
        this.addressToJpaAddressMapper = addressToJpaAddressMapper;
        this.jpaAddressToAddressMapper = jpaAddressToAddressMapper;
        this.addressJpaRepositoryHelper = addressJpaRepositoryHelper;
    }

    public Address save(Address address) {
        JpaAddress jpaAddress = addressToJpaAddressMapper.apply(address);
        jpaAddress = addressJpaRepositoryHelper.save(jpaAddress);
        return jpaAddressToAddressMapper.apply(jpaAddress);
    }

    public void remove(Address address) {
        addressJpaRepositoryHelper.delete(address.getId());
    }

    public Address findById(Long id) {
        JpaAddress jpaAddress = addressJpaRepositoryHelper.findOne(id);
        Address address = jpaAddressToAddressMapper.apply(jpaAddress);
        return address;
    }

    @Override
    public Address findByStreetAndZip(String street, String zip) {
        Address address = null;
        JpaAddress jpaAddress = null;
        try {
            jpaAddress = addressJpaRepositoryHelper.findByStreetAndZip(street, zip);
            address = jpaAddressToAddressMapper.apply(jpaAddress);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
}
