package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="addresses")
public class JpaAddress {

    private Long id;
    private String street;
    private String zip;

    private JpaCity city;
    private JpaState state;
    private JpaVenue venue;

    public JpaAddress() {}

    public JpaAddress(String street, String zip) {
        this.street = street;
        this.zip = zip;
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

    @Column(name="street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name="zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    public JpaCity getCity() {
        return city;
    }

    public void setCity(JpaCity city) {
        this.city = city;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    public JpaState getState() {
        return state;
    }

    public void setState(JpaState state) {
        this.state = state;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    public JpaVenue getVenue() {
        return venue;
    }

    public void setVenue(JpaVenue jpaVenue) {
        this.venue = jpaVenue;
    }
}