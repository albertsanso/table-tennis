package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationAlias;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.OrganizationAlias;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
public class JpaOrganizationToOrganizationMapper implements Function<JpaOrganization, Organization> {

    private JpaOrganizationAliasToOrganizationAliasMapper jpaOrganizationAliasToOrganizationAliasMapper;

    @Inject
    public JpaOrganizationToOrganizationMapper(@Lazy JpaOrganizationAliasToOrganizationAliasMapper jpaOrganizationAliasToOrganizationAliasMapper) {
        this.jpaOrganizationAliasToOrganizationAliasMapper = jpaOrganizationAliasToOrganizationAliasMapper;
    }

    @Override
    public Organization apply(JpaOrganization jpaOrganization) {
        if (jpaOrganization == null) return null;

        final Set<JpaOrganizationAlias> jpaOrganizationAliases = jpaOrganization.getOrganizationAliases();

        Set<OrganizationAlias> organizationAliases = jpaOrganizationAliases.stream()
                .map(obj -> jpaOrganizationAliasToOrganizationAliasMapper.apply(obj)).collect(Collectors.toSet());

        Organization.OrganizationBuilder builder = new Organization.OrganizationBuilder(
                jpaOrganization.getCodeName(),
                jpaOrganization.getName(),
                jpaOrganization.getType()
        );

        Organization organization = builder
                .withId(jpaOrganization.getId())
                .withAliases(organizationAliases)
                .withExternalId(jpaOrganization.getExternalId())
                .create();
        return organization;
    }
}
