package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="players")
public class JpaPlayer {

    private Long id;
    private String codeName;
    private String name;
    private String externalId;

    private Set<JpaTeamPlayer> teamPlayers = new HashSet<JpaTeamPlayer>();

    public JpaPlayer() {
    }

    public JpaPlayer(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @Column(name="external_id")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @OneToMany(mappedBy = "player", cascade={CascadeType.ALL})
    public Set<JpaTeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(Set<JpaTeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
