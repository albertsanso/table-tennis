package org.albertsanso.tabletennis.data;

public enum ResultKeys {
    DATE                        ("date"),
    TIME                        ("time"),
    LOCAL_TEAM_NAME             ("local_team_name"),
    VISITOR_TEAM_NAME           ("visitor_team_name"),
    LOCAL_TEAM_ID               ("local_team_id"),
    VISITOR_TEAM_ID             ("visitor_team_id"),
    LOCAL_POINTS                ("local_points"),
    VISITOR_POINTS              ("visitor_points"),
    MINUTES_URL                 ("minutes_url"),
    JORNADA_NUMBER              ("jornada_number"),
    CATEGORIA_ID                ("categoria_id")
    ;

    public String key;

    ResultKeys(String key) {
        this.key = key;
    }
}
