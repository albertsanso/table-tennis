package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="matches")
public class JpaMatch {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String day;
    private JpaVenue venue;
    private JpaTeam local;
    private JpaTeam visitor;

    public JpaMatch() {
    }

    public JpaMatch(JpaTeam local, JpaTeam visitor) {
        this.local = local;
        this.visitor = visitor;
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

    @Column(name="date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name="time")
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Column(name="day")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    public JpaVenue getVenue() {
        return venue;
    }

    public void setVenue(JpaVenue venue) {
        this.venue = venue;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_team_id")
    public JpaTeam getLocal() {
        return local;
    }

    public void setLocal(JpaTeam local) {
        this.local = local;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_team_id")
    public JpaTeam getVisitor() {
        return visitor;
    }

    public void setVisitor(JpaTeam visitor) {
        this.visitor = visitor;
    }
}
