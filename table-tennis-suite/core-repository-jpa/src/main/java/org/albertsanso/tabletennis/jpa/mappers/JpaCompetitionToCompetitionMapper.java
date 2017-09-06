package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.albertsanso.tabletennis.model.Competition;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaCompetitionToCompetitionMapper implements Function<JpaCompetition, Competition> {
    @Override
    public Competition apply(JpaCompetition jpaCompetition) {
        if (jpaCompetition == null) return null;

        Season season = Season.getByKey(jpaCompetition.getSeason());
        Competition.CompetitionBuilder builder = new Competition.CompetitionBuilder(
                jpaCompetition.getCodeName(),
                jpaCompetition.getName(),
                season
        );

        Competition competition = builder
                .withId(jpaCompetition.getId())
                .create();

        return competition;
    }
}
