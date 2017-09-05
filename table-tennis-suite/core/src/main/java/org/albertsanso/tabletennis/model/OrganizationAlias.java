package org.albertsanso.tabletennis.model;

public class OrganizationAlias extends ValueObject {

    private Long id;
    private String alias;
    private Organization organization;

    private OrganizationAlias() { super(); }

    private OrganizationAlias(Long id, String alias, Organization organization) {
        this.id = id;
        this.alias = alias;
        this.organization = organization;
    }

    private static OrganizationAlias createNewAlias(OrganizationAliasBuilder builder) {
        OrganizationAlias organizationAlias = new OrganizationAlias();
        return organizationAlias;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public Organization getOrganization() {
        return organization;
    }

    public static final class OrganizationAliasBuilder {

        private Long id;
        private String alias;
        private Organization organization;

        public OrganizationAliasBuilder(String alias, Organization organization) {
            this.alias = alias;
            this.organization = organization;
        }

        public Long getId() {
            return id;
        }

        public String getAlias() {
            return alias;
        }

        public Organization getOrganization() {
            return organization;
        }

        public OrganizationAliasBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OrganizationAlias create() { return createNewAlias(this); }
    }
}
