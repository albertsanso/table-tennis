package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="organization_aliases")
public class JpaOrganizationAlias {

    private Long id;
    private String alias;
    private JpaOrganization organization;

    public JpaOrganizationAlias() {};

    public JpaOrganizationAlias(String alias) {
        this.alias = alias;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="alias_name")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    public JpaOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(JpaOrganization organization) {
        this.organization = organization;
    }
}
