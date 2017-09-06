package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="competitions")
public class JpaCompetition {

    private Long id;
    private String codeName;
    private String name;
    private String season;

    private Set<JpaCategory> categories = new HashSet<JpaCategory>();
    private Set<JpaTeam> teams = new HashSet<JpaTeam>();

    public JpaCompetition() {}

    public JpaCompetition(String codeName, String name, String season) {
        this.codeName = codeName;
        this.name = name;
        this.season = season;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Column(name="code_name")
    public String getCodeName() { return codeName; }

    public void setCodeName(String codeName) { this.codeName = codeName; }

    @Column(name="name")
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Column(name="season")
    public String getSeason() { return season; }

    public void setSeason(String season) { this.season = season; }

    @OneToMany(mappedBy = "competition", cascade={CascadeType.ALL})
    public Set<JpaCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<JpaCategory> categories) {
        this.categories = categories;
    }

    @OneToMany(mappedBy = "competition", cascade={CascadeType.ALL})
    public Set<JpaTeam> getTeams() {
        return teams;
    }

    public void setTeams(Set<JpaTeam> teams) {
        this.teams = teams;
    }
}
