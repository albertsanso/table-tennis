package org.albertsanso.tabletennis.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class JpaCategory {

    private Long id;
    private String codeName;
    private String name;
    private String season;

    private JpaCompetition jpaCompetition;

    public JpaCategory() {}

    public JpaCategory(String codeName, String name, String season) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    public JpaCompetition getJpaCompetition() {
        return jpaCompetition;
    }

    public void setJpaCompetition(JpaCompetition jpaCompetition) {
        this.jpaCompetition = jpaCompetition;
    }
}
