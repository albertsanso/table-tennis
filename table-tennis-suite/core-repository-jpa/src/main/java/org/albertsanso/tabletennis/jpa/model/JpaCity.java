package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="cities")
public class JpaCity {

    private Long id;
    private String codeName;
    private String name;

    private JpaState state;

    public JpaCity() {}

    public JpaCity(String codeName, String name) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    public JpaState getState() {
        return state;
    }

    public void setState(JpaState state) {
        this.state = state;
    }
}