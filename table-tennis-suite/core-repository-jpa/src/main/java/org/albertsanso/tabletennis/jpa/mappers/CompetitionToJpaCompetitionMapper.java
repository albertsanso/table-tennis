package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.albertsanso.tabletennis.model.Competition;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class CompetitionToJpaCompetitionMapper implements Function<Competition, JpaCompetition> {
    @Override
    public JpaCompetition apply(Competition competition) {

        //Set<JpaCategory> categories = new HashSet<JpaCategory>();
        //Set<JpaTeam> teams = new HashSet<JpaTeam>();

        JpaCompetition jpaCompetition = new JpaCompetition(
                competition.getCodeName(), competition.getName(), competition.getSeason().seasonKey);
        jpaCompetition.setId(competition.getId());
        //jpaCompetition.setCategories(categories);
        //jpaCompetition.setTeams(teams);

        return jpaCompetition;
    }
}
