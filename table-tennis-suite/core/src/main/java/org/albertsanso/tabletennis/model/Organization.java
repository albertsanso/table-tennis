package org.albertsanso.tabletennis.model;

import java.util.HashSet;
import java.util.Set;

public class Organization extends Entity {

    private Long id;
    private String codeName;
    private String name;
    private String type;
    private String externalId;

    private Set<OrganizationAlias> organizationAliases = new HashSet<OrganizationAlias>();
    private Set<OrganizationVenue> organizationVenues = new HashSet<OrganizationVenue>();

    private Organization() { super(); }

    private Organization(Long id, String codeName, String name, String type, String externalId,  Set<OrganizationAlias> organizationAliases) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.type = type;
        this.externalId = externalId;
        this.organizationAliases = organizationAliases;
    }

    private static Organization createNewOrganization(OrganizationBuilder builder) {
        Organization organization = new Organization(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getType(),
                builder.getExternalId(),
                builder.getOrganizationAliases()
        );
        return organization;
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

    public String getType() {
        return type;
    }

    public String getExternalId() { return externalId; }

    public Set<OrganizationAlias> getOrganizationAliases() {
        return organizationAliases;
    }

    public void addOrganizationaAlias(OrganizationAlias organizationAlias) {
        this.organizationAliases.add(organizationAlias);
    }

    public Set<OrganizationVenue> getOrganizationVenues() {
        return organizationVenues;
    }

    public void addOrganizationVenue(OrganizationVenue organizationVenue) {
        this.organizationVenues.add(organizationVenue);
    }

    public static final class OrganizationBuilder {
        private Long id;
        private String codeName;
        private String name;
        private String type;
        private String externalId;
        private Set<OrganizationAlias> organizationAliases = new HashSet<OrganizationAlias>();
        private Set<OrganizationVenue> organizationVenues = new HashSet<OrganizationVenue>();

        public OrganizationBuilder(String codeName, String name, String type) {
            this.codeName = codeName;
            this.name = name;
            this.type = type;
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

        public String getType() {
            return type;
        }

        public String getExternalId() { return externalId; }

        public Set<OrganizationAlias> getOrganizationAliases() {
            return organizationAliases;
        }

        public Set<OrganizationVenue> getOrganizationVenues() {
            return organizationVenues;
        }

        public OrganizationBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OrganizationBuilder withExternalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public OrganizationBuilder withAliases(Set<OrganizationAlias> organizationAliases) {
            this.organizationAliases = organizationAliases;
            return this;
        }

        public OrganizationBuilder withVenues(Set<OrganizationVenue> organizationVenues) {
            this.organizationVenues = organizationVenues;
            return this;
        }

        public Organization create() { return createNewOrganization(this); }
    }
}
