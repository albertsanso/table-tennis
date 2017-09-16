package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper;

import org.albertsanso.tabletennis.data.TeamKeys;

public enum TeamMapper {
    COMPETITION_ID      (TeamKeys.COMPETITION_ID.key),

    TEAM_ID             (TeamKeys.TEAM_ID.key),
    TEAM_NAME           (TeamKeys.TEAM_NAME.key),
    TEAM_CATEGORY       (TeamKeys.TEAM_CATEGORY.key),
    TEAM_CATEGORY_ID    (TeamKeys.TEAM_CATEGORY_ID.key),

    PLAYER_ID               (TeamKeys.PLAYER_ID.key),
    PLAYER_NAME             (TeamKeys.PLAYER_NAME.key),
    PLAYER_CATEGORY         (TeamKeys.PLAYER_CATEGORY.key),
    PLAYER_NACIONALITY      (TeamKeys.PLAYER_NACIONALITY.key),
    PLAYER_PROCESS          (TeamKeys.PLAYER_PROCESS.key),

    SEASON                  (TeamKeys.SEASON.key)
    ;

    public String key;

    TeamMapper(String key) {
        this.key = key;
    }
}
