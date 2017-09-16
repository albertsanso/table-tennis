package org.albertsanso.tabletennis.model;

import org.albertsanso.tabletennis.data.Season;

public class Team extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private String externalId;
    private Season season;
    private Organization organization;
    private Category category;
    private Competition competition;

    private Team() {}

    private Team(Long id, String codeName, String name, String externalId, Season season, Organization organization, Category category, Competition competition) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.externalId = externalId;
        this.season = season;
        this.organization = organization;
        this.category = category;
        this.competition = competition;
    }

    private static Team createNewTeam(TeamBuilder builder) {
        Team team = new Team(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getExternalId(),
                builder.getSeason(),
                builder.getOrganization(),
                builder.getCategory(),
                builder.getCompetition()
        );
        return team;
    }

    public Long getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getName() {
        return name;
    }

    public String getExternalId() { return externalId; }

    public Season getSeason() {
        return season;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Category getCategory() {
        return category;
    }

    public Competition getCompetition() {
        return competition;
    }

    public static final class TeamBuilder {
        private Long id;
        private String codeName;
        private String name;
        private String externalId;
        private Season season;
        private Organization organization;
        private Category category;
        private Competition competition;

        public TeamBuilder(String codeName, String name, Season season) {
            this.codeName = codeName;
            this.name = name;
            this.season = season;
        }

        public Long getId() {
            return id;
        }

        public String getCodeName() {
            return codeName;
        }

        public String getName() {
            return name;
        }

        public String getExternalId() { return externalId; }

        public Season getSeason() {
            return season;
        }

        public Organization getOrganization() {
            return organization;
        }

        public Category getCategory() {
            return category;
        }

        public Competition getCompetition() {
            return competition;
        }

        public TeamBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TeamBuilder withExternalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public TeamBuilder withOrganization(Organization organization) {
            this.organization = organization;
            return this;
        }

        public TeamBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public TeamBuilder withCompetition(Competition competition) {
            this.competition = competition;
            return this;
        }

        public Team create() { return createNewTeam(this); }
    }
}
