package org.albertsanso.tabletennis.dataimport;

import org.albertsanso.tabletennis.data.OrganizationKeys;
import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.data.Util;
import org.albertsanso.tabletennis.model.*;
import org.albertsanso.tabletennis.port.*;
import org.albertsanso.util.SimpleCsvMapReader;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Named
@Transactional
public class Importer {

    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private AddressRepository addressRepository;
    private VenueRepository venueRepository;
    private OrganizationVenueRepository organizationVenueRepository;
    private OrganizationRepository organizationRepository;
    private SimpleCsvMapReader simpleCsvMapReader;

    @Inject
    public Importer(
            StateRepository stateRepository,
            CityRepository cityRepository,
            AddressRepository addressRepository,
            VenueRepository venueRepository,
            OrganizationVenueRepository organizationVenueRepository,
            OrganizationRepository organizationRepository,
            SimpleCsvMapReader simpleCsvMapReader
    ) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
        this.venueRepository = venueRepository;
        this.organizationVenueRepository= organizationVenueRepository;
        this.organizationRepository = organizationRepository;
        this.simpleCsvMapReader = simpleCsvMapReader;
    }

    public void doImport() throws Throwable {

        readOrganizations();
        String[] organizationsHeader = getOrganizationsHeader();
        List<Map<String, String>> organizationLines = getOrganizationsLines();

        importOrganizationsFromLines(organizationLines);
    }

    public void importOrganizationsFromLines(List<Map<String, String>> organizationLines) {
        for (Map<String, String> line : organizationLines) {
            importOrganizationFromLine(line);
        }
    }

    public void importOrganizationFromLine(Map<String, String> organizationLine) {

        State state = null;
        City city = null;
        Address address = null;
        Organization organization = null;
        Venue venue = null;
        OrganizationVenue organizationVenue = null;

        String stateName = organizationLine.get(OrganizationKeys.STATE.key);
        String cityName = organizationLine.get(OrganizationKeys.CITY.key);
        String zip = organizationLine.get(OrganizationKeys.ZIP.key);
        String street = organizationLine.get(OrganizationKeys.ADDRESS.key);
        String organizationName = organizationLine.get(OrganizationKeys.NAME.key);
        String venueName = "";
        String seasonKey = organizationLine.get(OrganizationKeys.SEASON.key);

        Season season = Season.getByKey(seasonKey);

        String organizationCodeName = "";
        String cityCodeName = "";
        String stateCodeName = "";
        String venueCodeName = "";

        try {
            organizationCodeName = Util.buildCodeName(organizationName);
            cityCodeName = Util.buildCodeName(cityName);
            stateCodeName = Util.buildCodeName(stateName);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        state = findOrCreateState(stateName, stateCodeName);
        //System.out.println(state);

        city = findOrCreateCity(cityCodeName, cityName, state);
        //System.out.println(city);

        address = findOrCreateAddress(street, zip, city, state);
        //System.out.println(address);

        organization = findOrCreateOrganization(organizationCodeName, organizationName, "club");
        //System.out.println(organization);

        venue = findOrCreateVenue(venueName, venueCodeName, organization, address, season);
        //System.out.println(venue);
    }

    private State findOrCreateState(String stateName, String stateCodeName) {
        State state = null;
        try {
            state = stateRepository.findByCodeName(stateCodeName);
            if (state == null) {
                State.StateBuilder builder = new State.StateBuilder(stateCodeName, stateName);
                state = builder.create();
                state = stateRepository.save(state);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return state;
    }

    private City findOrCreateCity(String cityCodeName, String cityName, State state) {
        City city = null;

        try {
            city = cityRepository.findByCodeName(cityCodeName);
            if (city == null) {
                City.CityBuilder builder = new City.CityBuilder(cityCodeName, cityName, state);
                city = builder.create();
                city = cityRepository.save(city);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return city;
    }

    private Address findOrCreateAddress(String street, String zip, City city, State state) {
        Address address = null;

        try {
            address = addressRepository.findByStreetAndZip(street, zip);
            if (address == null) {

                Address.AddressBuilder builder = new Address.AddressBuilder(zip, city, state);
                address = builder
                        .withStreet(street)
                        .create();
                address = addressRepository.save(address);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return address;
    }

    private Organization findOrCreateOrganization(String organizationCodeName, String organizationName, String type) {
        Organization organization = null;

        try {
            organization = organizationRepository.findByCodeName(organizationCodeName);
            if (organization == null) {

                Organization.OrganizationBuilder builder = new Organization.OrganizationBuilder(organizationCodeName, organizationName, type);
                organization = builder.create();
                organization = organizationRepository.save(organization);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return organization;
    }

    private Venue findOrCreateVenue(String venueName, String venueCodeName, Organization organization, Address address, Season season) {
        Venue venue = null;
        try {
            List<Venue> relatedVenues = getAllVenues(); // get all venues
            venue = guessVenueInListFromAddress(relatedVenues, address);
            if (venue == null) {

                List<OrganizationVenue> ovs = organizationVenueRepository.findByOrganization(organization);
                venueName = organization.getName()+" "+(ovs.size()+1);
                venueCodeName = Util.buildCodeName(venueName);

                Venue.VenueBuilder builder = new Venue.VenueBuilder(venueName, venueCodeName);
                venue = builder
                        .withAddress(address)
                        .create();
                venue = venueRepository.save(venue);
            }

            addVenueToOrganization(venue, organization, season);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return venue;
    }

    private void addVenueToOrganization(Venue venue, Organization organization, Season season) {

        OrganizationVenue existingOV = organizationVenueRepository.findOneByOrganizationAndVenueAndSeason(organization, venue, season);
        if (existingOV == null) {

            OrganizationVenue.OrganizationVenueBuilder builder = new OrganizationVenue.OrganizationVenueBuilder(
                organization, venue, season.seasonKey
            );

            /*
            OrganizationVenue organizationVenue = builder.create();
            organizationVenueRepository.save(organizationVenue);

            organization.addOrganizationVenue(organizationVenue);
            organizationRepository.save(organization);

            venue.addOrganizationVenue(organizationVenue);
            venueRepository.save(venue);
            */

            OrganizationVenue organizationVenue = builder.create();
            organization.addOrganizationVenue(organizationVenue);
            venue.addOrganizationVenue(organizationVenue);
            organizationVenueRepository.save(organizationVenue);

        }
    }

    private List<Venue> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();
        return venues;
    }

    private Venue guessVenueInListFromAddress(List<Venue> venuesList, Address capturedAddress) {
        for (Venue currentVenue : venuesList) {
            Address currentAddress = currentVenue.getAddress();
            if (matchAddresses(currentAddress, capturedAddress)) {
                System.out.println(">>>>>>>>>>>>> MATCH Addresses");
                System.out.println(">>>>>>>>>>>>> Captured Address: "+capturedAddress.getStreet());
                System.out.println(">>>>>>>>>>>>> Current Address: "+currentAddress.getStreet());
                return currentVenue;
            }
        }
        return null;
    }

    private boolean matchAddresses(Address adr1, Address adr2) {
        if (matchStates(adr1.getState().getName(), adr2.getState().getName(), 40)) {
            if (matchCities(adr1.getCity().getName(), adr2.getCity().getName(), 40)) {
                if (matchZips(adr1.getZip(), adr2.getZip(), 100)) {
                    if (matchStreets(adr1.getStreet(), adr2.getStreet(), 40)) {
                        return true;
                    }
                }
                else {
                    if (matchStreets(adr1.getStreet(), adr2.getStreet(), 30)) {
                        System.out.println(">>>>>>> NO CP");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean matchStates(String state1, String state2, int percent) {
        return Util.matchStrings(state1, state2, 90);
    }

    private boolean matchZips(String zip1, String zip2, int percent) {
        return Util.matchStrings(zip1, zip2, 100);
    }

    private boolean matchCities(String cityName1, String cityName2, int percent) {
        return Util.matchStrings(cityName1, cityName2, 40);
    }

    private boolean matchStreets(String street1, String street2, int percent) {
        return Util.matchStrings(street1, street2, 40);
    }

    protected void readOrganizations() throws IOException {
        simpleCsvMapReader.read();
    }

    protected String[] getOrganizationsHeader() {
        return simpleCsvMapReader.getHeader();
    }

    protected List<Map<String, String>> getOrganizationsLines() {
        return simpleCsvMapReader.getLines();
    }

    public void doTests() {
        // 5374
        Address address = addressRepository.findById(new Long(5374));
        System.out.println(address.getStreet()+", "+address.getZip()+", "+address.getVenue());

        /*
        State state = stateRepository.findById(new Long(944));
        System.out.println(state.getCodeName()+", "+state.getName());

        City city = cityRepository.findById(new Long(2440));
        System.out.println(city.getCodeName()+", "+city.getName());

        Address address = addressRepository.findById(new Long(3340));
        System.out.println(address.getStreet()+", "+address.getZip()+", "+address.getVenue());

        Venue venue = venueRepository.findById(new Long(2530));
        System.out.println(venue.getCodeName()+", "+venue.getName());

        OrganizationVenue organizationVenue = organizationVenueRepository.findById(new Long(3358));
        System.out.println(organizationVenue.getSeason());

        Organization organization = organizationRepository.findById(new Long(3019));
        System.out.println(organization);
        */

    }
}
