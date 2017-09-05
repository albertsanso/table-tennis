package org.albertsanso.tabletennis.model;

public class Address extends ValueObject {

    private Long id;
    private String street;
    private String zip;
    private City city;
    private State state;
    private Venue venue;

    private Address() { super(); }

    private Address(Long id, String street, String zip, City city, State state, Venue venue) {
        this.id = id;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.venue = venue;
    }

    private static Address creatNewAddres(AddressBuilder builder) {

        Address address = new Address(
                builder.getId(),
                builder.getStreet(),
                builder.getZip(),
                builder.getCity(),
                builder.getState(),
                builder.getVenue()
        );
        return address;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public City getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public Venue getVenue() {
        return venue;
    }

    public static final class AddressBuilder {

        private Long id;
        private String street;
        private String zip;
        private City city;
        private State state;
        private Venue venue;

        public AddressBuilder(String zip, City city, State state) {
            this.zip = zip;
            this.city = city;
            this.state = state;
            this.venue = venue;
        }

        public Long getId() {
            return id;
        }

        public String getStreet() {
            return street;
        }

        public String getZip() {
            return zip;
        }

        public City getCity() {
            return city;
        }

        public State getState() {
            return state;
        }

        public Venue getVenue() {
            return venue;
        }

        public AddressBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withVenue(Venue venue) {
            this.venue = venue;
            return this;
        }

        public Address create() { return creatNewAddres(this); }
    }
}
