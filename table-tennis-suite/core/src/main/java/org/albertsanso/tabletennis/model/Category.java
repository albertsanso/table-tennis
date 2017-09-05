package org.albertsanso.tabletennis.model;

public class Category extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private String season;
    private Competition competition;

    private Category() {}

    private Category(Long id, String codeName, String name, String season, Competition competition) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.season = season;
        this.competition = competition;
    }

    private static Category createNewCategory(CategoryBuilder builder) {
        Category category = new Category(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
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

    public String getSeason() {
        return season;
    }

    public Competition getCompetition() {
        return competition;
    }

    public static final class CategoryBuilder {
        private Long id;
        private String codeName;
        private String name;
        private String season;
        private Competition competition;

        public CategoryBuilder(String codeName, String name, String season) {
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

        public String getSeason() {
            return season;
        }

        public Competition getCompetition() {
            return competition;
        }

        public CategoryBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withCompetition(Competition competition) {
            this.competition = competition;
            return this;
        }

        public Category create() { return createNewCategory(this); }
    }
}
