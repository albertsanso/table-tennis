package org.albertsanso.tabletennis.model;

public class State extends ValueObject {

    private Long id;
    private String codeName;
    private String name;

    private State() { super(); }

    private State(Long id, String codeName, String name) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
    }

    private static State createNewState(StateBuilder builder) {
        State state = new State(
                builder.getId(),
                builder.getCodeName(),
                builder.getName()
        );
        return state;
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

    public static final class StateBuilder {

        private Long id;
        private String codeName;
        private String name;

        public StateBuilder(String codeName, String name) {
            this.codeName = codeName;
            this.name = name;
        }

        public Long getId() { return id; }

        public String getCodeName() {
            return codeName;
        }

        public String getName() {
            return name;
        }

        public StateBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public State create() {
            return createNewState(this);
        }
    }
}
