package org.albertsanso.tabletennis.data;

import java.time.LocalDate;
import java.time.Year;

public enum Season {
    CURRENT_SEASON (getCurrent()),
    SEASON_2017_2018 ("2017-2018"),
    SEASON_2016_2017 ("2016-2017"),
    SEASON_2015_2016 ("2015-2016"),
    SEASON_2014_2015 ("2014-2015"),
    SEASON_2013_2014 ("2013-2014"),
    SEASON_2012_2013 ("2012-2013"),
    SEASON_2011_2012 ("2011-2012"),
    SEASON_2010_2011 ("2010-2011"),
    SEASON_2009_2010 ("2009-2010"),
    SEASON_2008_2009 ("2008-2009")
    ;

    public String seasonKey;

    Season(String season) {
        this.seasonKey = season;
    }

    private static String getCurrent() {
        int currentYear = Year.now().getValue();
        return getSeasonRange(currentYear);
    }

    private static String getSeasonRange(int year) {
        int yearBegin = year, yearEnd = year;
        int currentMonth = LocalDate.now().getMonthValue();
        if (currentMonth >= 9) {
            yearEnd++;
        } else {
            yearBegin--;
        }
        return ""+yearBegin+"-"+yearEnd;
    }

    public static Season getByKey(String key) {
        for (Season season : Season.values()) {
            if (season.seasonKey.equals(key)) {
                return season;
            }
        }
        return null;
    }

    public boolean equals(Season aSeason) {
        return this.seasonKey.equals(aSeason.seasonKey);
    }

    public static void main(String[] args) throws Throwable {
        System.out.println(getCurrent());
        System.out.println(LocalDate.now().getMonthValue());
        System.out.println(getSeasonRange(2016));
    }

}