package org.albertsanso.tabletennis.model;

public class City extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private State state;

    private City() { super(); }

    private City(Long id, String codeName, String name, State state) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.state = state;
    }

    private static City createNewCity(CityBuilder builder) {
        City city = new City(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getState()
        );
        return city;
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

    public State getState() {
        return state;
    }

    public static final class CityBuilder {
        private Long id;
        private String codeName;
        private String name;
        private State state;

        public CityBuilder(String codeName, String name, State state) {
            this.codeName = codeName;
            this.name = name;
            this.state = state;
        }

        public Long getId() { return id; }

        public String getCodeName() {
            return codeName;
        }

        public String getName() {
            return name;
        }

        public State getState() {
            return state;
        }

        public CityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public City create() { return createNewCity(this); }
    }
}
