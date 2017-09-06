package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.jpa.model.JpaCategory;
import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaTeam;
import org.albertsanso.tabletennis.model.Category;
import org.albertsanso.tabletennis.model.Competition;
import org.albertsanso.tabletennis.model.Organization;
import org.albertsanso.tabletennis.model.Team;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaTeamToTeamMapper implements Function<JpaTeam, Team> {

    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;
    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;
    private JpaCategoryToCategoryMapper jpaCategoryToCategoryMapper;

    @Inject
    public JpaTeamToTeamMapper(
            JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper,
            JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper,
            JpaCategoryToCategoryMapper jpaCategoryToCategoryMapper
    ) {
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
        this.jpaCategoryToCategoryMapper = jpaCategoryToCategoryMapper;
    }

    @Override
    public Team apply(JpaTeam jpaTeam) {
        if (jpaTeam == null) return null;

        Organization organization = jpaOrganizationToOrganizationMapper.apply(jpaTeam.getOrganization());
        Competition competition = jpaCompetitionToCompetitionMapper.apply(jpaTeam.getCompetition());
        Category category = jpaCategoryToCategoryMapper.apply(jpaTeam.getCategory());

        Season season = Season.getByKey(jpaTeam.getSeason());
        Team.TeamBuilder builder = new Team.TeamBuilder(jpaTeam.getCodeName(), jpaTeam.getName(), season);
        Team team = builder.withId(jpaTeam.getId())
                .withCompetition(competition)
                .withCategory(category)
                .withOrganization(organization)
                .create();

        return team;
    }
}
