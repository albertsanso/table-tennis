package org.albertsanso.tabletennis.model;

public class Player extends ValueObject {

    private Long id;
    private String codeName;
    private String name;
    private String externalId;

    private Player() {
    }

    private Player(Long id, String codeName, String name, String externalId) {
        this.id = id;
        this.codeName = codeName;
        this.name = name;
        this.externalId = externalId;
    }

    private static Player createNewPlayer(PlayerBuilder builder) {
        Player player = new Player(
                builder.getId(),
                builder.getCodeName(),
                builder.getName(),
                builder.getExternalId()
        );
        return player;
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

    public String getExternalId() {
        return externalId;
    }

    public static final class PlayerBuilder {
        private Long id;
        private String codeName;
        private String name;
        private String externalId;

        public PlayerBuilder(String codeName, String name) {
            this.id = id;
            this.codeName = codeName;
            this.name = name;
            this.externalId = externalId;
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

        public String getExternalId() {
            return externalId;
        }

        public PlayerBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PlayerBuilder withExternalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public Player create() { return createNewPlayer(this); }
    }
}
