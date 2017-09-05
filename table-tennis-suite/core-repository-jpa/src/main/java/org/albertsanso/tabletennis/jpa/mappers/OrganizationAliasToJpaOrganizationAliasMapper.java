package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationAlias;
import org.albertsanso.tabletennis.model.OrganizationAlias;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class OrganizationAliasToJpaOrganizationAliasMapper implements Function<OrganizationAlias, JpaOrganizationAlias> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;

    @Inject
    public OrganizationAliasToJpaOrganizationAliasMapper(OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
    }

    @Override
    public JpaOrganizationAlias apply(OrganizationAlias organizationAlias) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organizationAlias.getOrganization());
        JpaOrganizationAlias jpaAlias = new JpaOrganizationAlias(organizationAlias.getAlias());
        jpaAlias.setAlias(organizationAlias.getAlias());
        jpaAlias.setId(organizationAlias.getId());
        jpaAlias.setOrganization(jpaOrganization);
        return jpaAlias;
    }
}
