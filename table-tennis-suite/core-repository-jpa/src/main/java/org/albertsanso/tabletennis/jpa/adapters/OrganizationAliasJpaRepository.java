package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.JpaOrganizationAliasToOrganizationAliasMapper;
import org.albertsanso.tabletennis.jpa.mappers.OrganizationAliasToJpaOrganizationAliasMapper;
import org.albertsanso.tabletennis.jpa.model.JpaOrganizationAlias;
import org.albertsanso.tabletennis.jpa.repository.OrganizationAliasJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.OrganizationAlias;
import org.albertsanso.tabletennis.port.OrganizationAliasRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationAliasJpaRepository implements OrganizationAliasRepository {

    private OrganizationAliasJpaRepositoryHelper organizationAliasJpaRepositoryHelper;
    private OrganizationAliasToJpaOrganizationAliasMapper organizationAliasToJpaOrganizationAliasMapper;
    private JpaOrganizationAliasToOrganizationAliasMapper jpaOrganizationAliasToOrganizationAliasMapper;

    @Inject
    public OrganizationAliasJpaRepository(OrganizationAliasJpaRepositoryHelper organizationAliasJpaRepositoryHelper, OrganizationAliasToJpaOrganizationAliasMapper organizationAliasToJpaOrganizationAliasMapper, JpaOrganizationAliasToOrganizationAliasMapper jpaOrganizationAliasToOrganizationAliasMapper) {
        this.organizationAliasJpaRepositoryHelper = organizationAliasJpaRepositoryHelper;
        this.organizationAliasToJpaOrganizationAliasMapper = organizationAliasToJpaOrganizationAliasMapper;
        this.jpaOrganizationAliasToOrganizationAliasMapper = jpaOrganizationAliasToOrganizationAliasMapper;
    }

    public OrganizationAlias save(OrganizationAlias organizationAlias) {
        JpaOrganizationAlias jpaOrganizationAlias = organizationAliasToJpaOrganizationAliasMapper.apply(organizationAlias);
        return jpaOrganizationAliasToOrganizationAliasMapper.apply(organizationAliasJpaRepositoryHelper.save(jpaOrganizationAlias));
    }
    public void remove(OrganizationAlias organizationAlias) {
        organizationAliasJpaRepositoryHelper.delete(organizationAlias.getId());
    }

    public OrganizationAlias findById(Long id) {
        JpaOrganizationAlias jpaOrganizationAlias = organizationAliasJpaRepositoryHelper.findOne(id);
        OrganizationAlias organizationAlias = jpaOrganizationAliasToOrganizationAliasMapper.apply(jpaOrganizationAlias);
        return organizationAlias;
    }
}
