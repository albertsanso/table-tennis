package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="venues")
public class JpaVenue {
    private Long id;
    private String codeName;
    private String name;

    private Set<JpaOrganizationVenue> organizationVenues = new HashSet<JpaOrganizationVenue>();
    private JpaAddress address;

    public JpaVenue() {}

    public JpaVenue(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
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

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    public Set<JpaOrganizationVenue> getOrganizationVenues() {
        return organizationVenues;
    }

    public void setOrganizationVenues(Set<JpaOrganizationVenue> organizationVenues) {
        this.organizationVenues = organizationVenues;
    }

    public void addOrganizationVenue(JpaOrganizationVenue jpaOrganizationVenue) {
        this.organizationVenues.add(jpaOrganizationVenue);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    public JpaAddress getAddress() {
        return address;
    }

    public void setAddress(JpaAddress jpaAddress) {
        this.address = jpaAddress;
    }
}
