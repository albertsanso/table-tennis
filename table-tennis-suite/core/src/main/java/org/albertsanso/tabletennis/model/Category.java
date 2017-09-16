package org.albertsanso.tabletennis.model;

import org.albertsanso.tabletennis.data.Season;

public class Category extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private String externalId;
    private Season season;
    private Competition competition;

    private Category() {}

    private Category(Long id, String codeName, String name, String externalId, Season season, Competition competition) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.externalId = externalId;
        this.season = season;
        this.competition = competition;
    }

    private static Category createNewCategory(CategoryBuilder builder) {
        Category category = new Category(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getExternalId(),
                builder.getSeason(),
                builder.getCompetition()
        );
        return category;
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

    public Competition getCompetition() {
        return competition;
    }

    public static final class CategoryBuilder {
        private Long id;
        private String codeName;
        private String name;
        private String externalId;
        private Season season;
        private Competition competition;

        public CategoryBuilder(String codeName, String name, Season season) {
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

        public Competition getCompetition() {
            return competition;
        }

        public CategoryBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withExternalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public CategoryBuilder withCompetition(Competition competition) {
            this.competition = competition;
            return this;
        }

        public Category create() { return createNewCategory(this); }
    }
}
