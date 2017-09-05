package org.albertsanso.tabletennis.model;

public class OrganizationVenue extends ValueObject {

    private Long id;
    private Organization organization;
    private Venue venue;
    private String season;

    private OrganizationVenue() { super(); }

    private OrganizationVenue(Long id, Organization organization, Venue venue, String season) {
        this.id = id;
        this.organization = organization;
        this.venue = venue;
        this.season = season;
    }

    private static OrganizationVenue createNewOrganizationVenue(OrganizationVenueBuilder builder) {
        OrganizationVenue ov = new OrganizationVenue(
                builder.getId(),
                builder.getOrganization(),
                builder.getVenue(),
                builder.getSeason()
        );
        return ov;
    }

    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getSeason() {
        return season;
    }

    public static final class OrganizationVenueBuilder {

        private Long id;
        private Organization organization;
        private Venue venue;
        private String season;

        public OrganizationVenueBuilder(Organization organization, Venue venue, String season) {
            this.organization = organization;
            this.venue = venue;
            this.season = season;
        }

        public Long getId() {
            return id;
        }

        public Organization getOrganization() {
            return organization;
        }

        public Venue getVenue() {
            return venue;
        }

        public String getSeason() {
            return season;
        }

        public OrganizationVenueBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OrganizationVenue create() { return createNewOrganizationVenue(this); }
    }
}
