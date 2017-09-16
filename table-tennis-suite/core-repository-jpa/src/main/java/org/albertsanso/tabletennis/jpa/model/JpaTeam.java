package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="teams")
public class JpaTeam {

    private Long id;
    private String codeName;
    private String name;
    private String season;
    private String externalId;

    private JpaOrganization organization;
    private JpaCompetition competition;
    private JpaCategory category;

    private Set<JpaTeamPlayer> teamPlayers = new HashSet<JpaTeamPlayer>();

    public JpaTeam() {}

    public JpaTeam(String codeName, String name, String season) {
        this.codeName = codeName;
        this.name = name;
        this.season = season;
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

    @Column(name="season")
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Column(name="external_id")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    public JpaOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(JpaOrganization organization) {
        this.organization = organization;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    public JpaCompetition getCompetition() {
        return competition;
    }

    public void setCompetition(JpaCompetition competition) {
        this.competition = competition;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public JpaCategory getCategory() {
        return category;
    }

    public void setCategory(JpaCategory category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "player", cascade={CascadeType.ALL})
    public Set<JpaTeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(Set<JpaTeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
