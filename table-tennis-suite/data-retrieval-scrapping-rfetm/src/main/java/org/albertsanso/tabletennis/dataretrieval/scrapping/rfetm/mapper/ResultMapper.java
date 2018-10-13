package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper;

import org.albertsanso.tabletennis.data.ResultKeys;

public enum ResultMapper {
    DATE                        (ResultKeys.DATE.key),
    TIME                        (ResultKeys.TIME.key),
    LOCAL_TEAM_NAME             (ResultKeys.LOCAL_TEAM_NAME.key),
    VISITOR_TEAM_NAME           (ResultKeys.VISITOR_TEAM_NAME.key),
    LOCAL_TEAM_ID               (ResultKeys.LOCAL_TEAM_ID.key),
    VISITOR_TEAM_ID             (ResultKeys.VISITOR_TEAM_ID.key),
    LOCAL_POINTS                (ResultKeys.LOCAL_POINTS.key),
    VISITOR_POINTS              (ResultKeys.VISITOR_POINTS.key),
    MINUTES_URL                 (ResultKeys.MINUTES_URL.key),
    JORNADA_NUMBER              (ResultKeys.JORNADA_NUMBER.key),
    CATEGORIA_ID                (ResultKeys.CATEGORIA_ID.key)
    ;

    public String key;

    ResultMapper(String key) {
        this.key = key;
    }
}
