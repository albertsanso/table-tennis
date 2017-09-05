package org.albertsanso.tabletennis.model;

import org.albertsanso.tabletennis.data.Season;

public class Competition extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private Season season;

    private Competition() {}

    private Competition(Long id, String codeName, String name, Season season) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.season = season;
    }

    private static Competition createNewCompetition(CompetitionBuilder builder) {
        Competition competition = new Competition(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getSeason()
        );
        return competition;
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

    public Season getSeason() {
        return season;
    }

    public static final class CompetitionBuilder {
        private Long id;
        private String codeName;
        private String name;
        private Season season;

        public CompetitionBuilder(String codeName, String name, Season season) {
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

        public Season getSeason() {
            return season;
        }

        public CompetitionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public Competition create() { return createNewCompetition(this); }
    }
}
