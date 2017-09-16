package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper;

public enum QualificationMapper {

    GROUP_DESCRIPTION("group_description", 1);

    public String key;
    public int position;

    QualificationMapper(String key, int position) {
        this.key = key;
        this.position = position;
    }

    public static QualificationMapper getByPosition(int requestedPosition) {
        QualificationMapper N = null;
        for (QualificationMapper m : QualificationMapper.values()) {
            if (m.position == requestedPosition) {
                return m;
            }
        }
        return null;
    }
}
