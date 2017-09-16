package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="teams_players")
public class JpaTeamPlayer {
    private Long id;
    private JpaTeam team;
    private JpaPlayer player;

    public JpaTeamPlayer() {
    }

    public JpaTeamPlayer(JpaTeam team, JpaPlayer player) {
        this.team = team;
        this.player = player;
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

    @ManyToOne
    @JoinColumn(name="team_id")
    public JpaTeam getTeam() {
        return team;
    }

    public void setTeam(JpaTeam team) {
        this.team = team;
    }

    @ManyToOne
    @JoinColumn(name="player_id")
    public JpaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(JpaPlayer player) {
        this.player = player;
    }
}
