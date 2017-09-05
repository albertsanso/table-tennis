package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaOrganizationAlias;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.OrganizationAlias;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaOrganizationAliasToOrganizationAliasMapper implements Function<JpaOrganizationAlias, OrganizationAlias> {

    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;

    @Inject
    public JpaOrganizationAliasToOrganizationAliasMapper(@Lazy JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper) {
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
    }

    @Override
    public OrganizationAlias apply(JpaOrganizationAlias jpaOrganizationAlias) {
        if (jpaOrganizationAlias == null) return null;

        Organization organization = jpaOrganizationToOrganizationMapper.apply(jpaOrganizationAlias.getOrganization());

        OrganizationAlias.OrganizationAliasBuilder builder = new OrganizationAlias.OrganizationAliasBuilder(
                jpaOrganizationAlias.getAlias(),
                organization
        );

        OrganizationAlias alias = builder
                .withId(jpaOrganizationAlias.getId())
                .create();
        return alias;
    }
}
