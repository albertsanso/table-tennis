package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="organizations_venues")
public class JpaOrganizationVenue {

    private Long id;
    private JpaOrganization organization;
    private JpaVenue venue;

    private String season;

    public JpaOrganizationVenue() {}

    public JpaOrganizationVenue(JpaOrganization jpaOrganization, JpaVenue jpaVenue, String season) {
        this.organization = jpaOrganization;
        this.venue = jpaVenue;
        this.season = season;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "organizations_venues_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "organization_id")
    public JpaOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(JpaOrganization organization) {
        this.organization = organization;
    }

    @ManyToOne
    @JoinColumn(name = "venue_id")
    public JpaVenue getVenue() {
        return venue;
    }

    public void setVenue(JpaVenue venue) {
        this.venue = venue;
    }

    @Column(name = "season")
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}