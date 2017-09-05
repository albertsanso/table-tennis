package org.albertsanso.tabletennis.data;

public enum OrganizationKeys {
    NAME                    ("name"),
    CLUB_ID                 ("club_id"),
    CALENDAR_NAME           ("calendar_name"),
    ADDRESS                 ("address"),
    ZIP                     ("zip"),
    CITY                    ("city"),
    STATE                   ("state"),
    TELEPHONE               ("telephone"),
    FAX                     ("fax"),
    GAME_VENUE_NAME         ("game_venue_name"),
    GAME_VENUE_ADDRESS      ("game_venue_address"),
    GAME_VENUE_CITY         ("game_venue_city"),
    GAME_VENUE_STATE        ("game_venue_state"),
    CONTACT_PERSON          ("contact_person"),
    CONTACT_TELEPHONE       ("contact_telephone"),
    EMAIL                   ("email"),
    BALL_BRAND              ("ball_brand"),
    BALL_COLOR              ("ball_color"),
    TABLE_BRAND             ("table_brand"),
    TABLE_MODEL             ("table_model"),
    TABLE_COLOR             ("table_color"),
    SHIRT_COLOR             ("shirt_color"),
    SEASON                  ("season");

    public String key;

    OrganizationKeys(String key) {
        this.key = key;
    }

}
