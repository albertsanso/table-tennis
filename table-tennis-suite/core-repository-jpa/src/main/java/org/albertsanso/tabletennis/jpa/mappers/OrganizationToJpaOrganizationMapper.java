package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationAlias;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationVenue;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.OrganizationAlias;
import org.albertsanso.tabletennis.model.OrganizationVenue;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Named
public class OrganizationToJpaOrganizationMapper implements Function<Organization, JpaOrganization> {

    private OrganizationAliasToJpaOrganizationAliasMapper organizationAliasToJpaOrganizationAliasMapper;
    private OrganizationVenueToJpaOrganizationVenueMapper organizationVenueToJpaOrganizationVenueMapper;

    @Inject
    public OrganizationToJpaOrganizationMapper(
            @Lazy OrganizationAliasToJpaOrganizationAliasMapper organizationAliasToJpaOrganizationAliasMapper,
            @Lazy OrganizationVenueToJpaOrganizationVenueMapper organizationVenueToJpaOrganizationVenueMapper)
    {
        this.organizationAliasToJpaOrganizationAliasMapper = organizationAliasToJpaOrganizationAliasMapper;
        this.organizationVenueToJpaOrganizationVenueMapper = organizationVenueToJpaOrganizationVenueMapper;
    }

    @Override
    public JpaOrganization apply(Organization organization) {

        Set<JpaOrganizationVenue> jpaOvsSet = new HashSet<JpaOrganizationVenue>();
        Set<JpaOrganizationAlias> jpaOasSet = new HashSet<JpaOrganizationAlias>();

        JpaOrganization jpaOrganization = new JpaOrganization(organization.getCodeName(), organization.getName(), organization.getType());
        jpaOrganization.setId(organization.getId());
        jpaOrganization.setOrganizationAliases(jpaOasSet);
        jpaOrganization.setOrganizationVenues(jpaOvsSet);

        return jpaOrganization;
    }
}
