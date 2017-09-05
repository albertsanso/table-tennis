package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.JpaOrganizationToOrganizationMapper;
import org.albertsanso.tabletennis.jpa.mappers.OrganizationToJpaOrganizationMapper;
import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.repository.OrganizationJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.port.OrganizationRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationJpaRepository implements OrganizationRepository {

    private OrganizationJpaRepositoryHelper organizationJpaRepositoryHelper;
    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;

    @Inject
    public OrganizationJpaRepository(OrganizationJpaRepositoryHelper organizationJpaRepositoryHelper, OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper, JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper) {
        this.organizationJpaRepositoryHelper = organizationJpaRepositoryHelper;
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
    }

    public Organization save(Organization organization) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organization);
        return jpaOrganizationToOrganizationMapper.apply(organizationJpaRepositoryHelper.save(jpaOrganization));
    }
    public void remove(Organization organization) {
        organizationJpaRepositoryHelper.delete(organization.getId());
    }

    public Organization findById(Long id) {
        JpaOrganization jpaOrganization = organizationJpaRepositoryHelper.findOne(id);
        Organization organization = jpaOrganizationToOrganizationMapper.apply(jpaOrganization);
        return organization;
    }

    @Override
    public Organization findByCodeName(String codeName) {
        JpaOrganization jpaOrganization = organizationJpaRepositoryHelper.findByCodeName(codeName);
        Organization organization = jpaOrganizationToOrganizationMapper.apply(jpaOrganization);
        return organization;
    }
}
