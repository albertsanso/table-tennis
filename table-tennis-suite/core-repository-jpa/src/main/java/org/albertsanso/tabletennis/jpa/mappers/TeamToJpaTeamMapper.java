package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaCategory;
import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.albertsanso.tabletennis.jpa.model.JpaOrganization;
import org.albertsanso.tabletennis.jpa.model.JpaTeam;
import org.albertsanso.tabletennis.model.Team;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class TeamToJpaTeamMapper implements Function<Team, JpaTeam> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper;
    private CategoryToJpaCategoryMapper categoryToJpaCategoryMapper;

    @Inject
    public TeamToJpaTeamMapper(OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper, CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper, CategoryToJpaCategoryMapper categoryToJpaCategoryMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.competitionToJpaCompetitionMapper = competitionToJpaCompetitionMapper;
        this.categoryToJpaCategoryMapper = categoryToJpaCategoryMapper;
    }

    @Override
    public JpaTeam apply(Team team) {

        JpaTeam jpaTeam = new JpaTeam(team.getCodeName(), team.getName(), team.getSeason().seasonKey);

        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(team.getCompetition());
        JpaCategory jpaCategory = categoryToJpaCategoryMapper.apply(team.getCategory());
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(team.getOrganization());

        jpaTeam.setCompetition(jpaCompetition);
        jpaTeam.setCategory(jpaCategory);
        jpaTeam.setOrganization(jpaOrganization);

        return jpaTeam;
    }
}
