package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="alignments")
public class JpaMatchAlignment {
    private Long id;
    private JpaMatch match;
    private JpaTeamPlayer localTeamPlayer;
    private JpaTeamPlayer visitorTeamPlayer;

    public JpaMatchAlignment(Long id) {
        this.id = id;
    }

    public JpaMatchAlignment(Long id, JpaMatch match, JpaTeamPlayer localTeamPlayer, JpaTeamPlayer visitorTeamPlayer) {
        this.id = id;
        this.match = match;
        this.localTeamPlayer = localTeamPlayer;
        this.visitorTeamPlayer = visitorTeamPlayer;
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

    @ManyToOne
    @JoinColumn(name="match_id")
    public JpaMatch getMatch() {
        return match;
    }

    public void setMatch(JpaMatch match) {
        this.match = match;
    }

    @ManyToOne
    @JoinColumn(name="teams_players_id")
    public JpaTeamPlayer getLocalTeamPlayer() {
        return localTeamPlayer;
    }

    public void setLocalTeamPlayer(JpaTeamPlayer localTeamPlayer) {
        this.localTeamPlayer = localTeamPlayer;
    }

    public JpaTeamPlayer getVisitorTeamPlayer() {
        return visitorTeamPlayer;
    }

    public void setVisitorTeamPlayer(JpaTeamPlayer visitorTeamPlayer) {
        this.visitorTeamPlayer = visitorTeamPlayer;
    }
}
