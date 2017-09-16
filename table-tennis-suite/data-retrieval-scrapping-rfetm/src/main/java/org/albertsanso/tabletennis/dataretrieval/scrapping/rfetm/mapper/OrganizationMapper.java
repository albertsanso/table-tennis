package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper;

import org.albertsanso.tabletennis.data.OrganizationKeys;

public enum OrganizationMapper {
    NAME                    (OrganizationKeys.NAME.key, 1),
    CLUB_ID                 (OrganizationKeys.CLUB_ID.key,2),
    CALENDAR_NAME           (OrganizationKeys.CALENDAR_NAME.key, 3),
    ADDRESS                 (OrganizationKeys.ADDRESS.key, 4),
    ZIP                     (OrganizationKeys.ZIP.key, 5),
    CITY                    (OrganizationKeys.CITY.key, 6),
    STATE                   (OrganizationKeys.STATE.key, 7),
    TELEPHONE               (OrganizationKeys.TELEPHONE.key, 8),
    FAX                     (OrganizationKeys.FAX.key, 9),
    GAME_VENUE_NAME         (OrganizationKeys.GAME_VENUE_NAME.key, 10),
    GAME_VENUE_ADDRESS      (OrganizationKeys.GAME_VENUE_ADDRESS.key, 11),
    GAME_VENUE_CITY         (OrganizationKeys.GAME_VENUE_CITY.key, 12),
    GAME_VENUE_STATE        (OrganizationKeys.GAME_VENUE_STATE.key, 13),
    CONTACT_PERSON          (OrganizationKeys.CONTACT_PERSON.key, 14),
    CONTACT_TELEPHONE       (OrganizationKeys.CONTACT_TELEPHONE.key, 15),
    EMAIL                   (OrganizationKeys.EMAIL.key, 16),
    BALL_BRAND              (OrganizationKeys.BALL_BRAND.key, 17),
    BALL_COLOR              (OrganizationKeys.BALL_COLOR.key, 18),
    TABLE_BRAND             (OrganizationKeys.TABLE_BRAND.key, 19),
    TABLE_MODEL             (OrganizationKeys.TABLE_MODEL.key, 20),
    TABLE_COLOR             (OrganizationKeys.TABLE_COLOR.key, 21),
    SHIRT_COLOR             (OrganizationKeys.SHIRT_COLOR.key, 22);

    public String key;
    public int position;

    OrganizationMapper(String key, int position) {
        this.key = key;
        this.position = position;
    }

    public static OrganizationMapper getByPosition(int requestedPosition) {
        OrganizationMapper N = null;
        for (OrganizationMapper m : OrganizationMapper.values()) {
            if (m.position == requestedPosition) {
                return m;
            }
        }
        return null;
    }
}