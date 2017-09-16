package org.albertsanso.tabletennis.data;

public enum TeamKeys {

    COMPETITION_ID          ("lica_nacional_española"),

    TEAM_ID                 ("team_id"),
    TEAM_NAME               ("team_name"),
    TEAM_CATEGORY           ("team_category"),
    TEAM_CATEGORY_ID        ("team_category_id"),

    PLAYER_ID               ("player_id"),
    PLAYER_NAME             ("player_name"),
    PLAYER_CATEGORY         ("player_category"),
    PLAYER_NACIONALITY      ("player_nacionality"),
    PLAYER_PROCESS          ("player_process"),

    SEASON                  ("season")
    ;

    public String key;

    TeamKeys(String key) {
        this.key = key;
    }
}
