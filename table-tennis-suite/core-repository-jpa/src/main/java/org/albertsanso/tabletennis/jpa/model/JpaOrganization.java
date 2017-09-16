package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="organizations")
public class JpaOrganization {

    private Long id;
    private String codeName;
    private String name;
    private String type;
    private String externalId;

    private Set<JpaOrganizationVenue> organizationVenues = new HashSet<JpaOrganizationVenue>();
    private Set<JpaOrganizationAlias> organizationAliases = new HashSet<JpaOrganizationAlias>();
    private Set<JpaTeam> teams = new HashSet<JpaTeam>();

    public JpaOrganization() {}

    public JpaOrganization(String codeName, String name, String type) {
        this.codeName = codeName;
        this.name = name;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="code_name")
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="external_id")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @OneToMany(mappedBy = "organization", cascade={CascadeType.ALL})
    public Set<JpaOrganizationVenue> getOrganizationVenues() {
        return organizationVenues;
    }

    public void setOrganizationVenues(Set<JpaOrganizationVenue> organizationVenues) {
        this.organizationVenues = organizationVenues;
    }

    public void addOrganizationVenue(JpaOrganizationVenue jpaOrganizationVenue) {
        this.organizationVenues.add(jpaOrganizationVenue);
    }

    @OneToMany(mappedBy = "organization", cascade={CascadeType.ALL})
    public Set<JpaOrganizationAlias> getOrganizationAliases() {
        return organizationAliases;
    }

    public void setOrganizationAliases(Set<JpaOrganizationAlias> organizationAliases) {
        this.organizationAliases = organizationAliases;
    }

    public void addOrganizationAlias(JpaOrganizationAlias jpaOrganizationAlias) {
        this.organizationAliases.add(jpaOrganizationAlias);
    }

    @OneToMany(mappedBy = "organization", cascade={CascadeType.ALL})
    public Set<JpaTeam> getTeams() {
        return teams;
    }

    public void setTeams(Set<JpaTeam> teams) {
        this.teams = teams;
    }
}
