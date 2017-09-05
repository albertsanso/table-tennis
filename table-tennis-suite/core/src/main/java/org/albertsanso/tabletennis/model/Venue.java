package org.albertsanso.tabletennis.model;

import java.util.HashSet;
import java.util.Set;

public class Venue extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private Address address;

    private Set<OrganizationVenue> organizationVenues = new HashSet<OrganizationVenue>();

    private Venue() { super(); }

    private Venue(Long id, String codeName, String name, Address address) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.address = address;
    }

    private static Venue createNewVenue(VenueBuilder builder) {
        Venue venue = new Venue(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getAddress()
        );
        return venue;
    }

    public Long getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Set<OrganizationVenue> getOrganizationVenues() {
        return organizationVenues;
    }

    public void setOrganizationVenues(Set<OrganizationVenue> organizationVenues) {
        this.organizationVenues = organizationVenues;
    }

    public void addOrganizationVenue(OrganizationVenue organizationVenue) {
        this.organizationVenues.add(organizationVenue);
    }

    public static final class VenueBuilder {
        private Long id;
        private String codeName;
        private String name;
        private Address address;

        public VenueBuilder(String codeName, String name) {
            this.codeName = codeName;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getCodeName() {
            return codeName;
        }

        public String getName() {
            return name;
        }

        public Address getAddress() {
            return address;
        }

        public VenueBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public VenueBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public Venue create() { return createNewVenue(this); }
    }
}
