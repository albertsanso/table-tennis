package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.JpaTeamToTeamMapper;
import org.albertsanso.tabletennis.jpa.mappers.TeamToJpaTeamMapper;
import org.albertsanso.tabletennis.jpa.model.JpaTeam;
import org.albertsanso.tabletennis.jpa.repository.TeamJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Team;
import org.albertsanso.tabletennis.port.TeamRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TeamJpaRepository implements TeamRepository {

    private TeamToJpaTeamMapper teamToJpaTeamMapper;
    private JpaTeamToTeamMapper jpaTeamToTeamMapper;
    private TeamJpaRepositoryHelper teamJpaRepositoryHelper;

    @Inject
    public TeamJpaRepository(TeamToJpaTeamMapper teamToJpaTeamMapper, JpaTeamToTeamMapper jpaTeamToTeamMapper, TeamJpaRepositoryHelper teamJpaRepositoryHelper) {
        this.teamToJpaTeamMapper = teamToJpaTeamMapper;
        this.jpaTeamToTeamMapper = jpaTeamToTeamMapper;
        this.teamJpaRepositoryHelper = teamJpaRepositoryHelper;
    }

    @Override
    public Team findById(Long id) {
        JpaTeam jpaTeam = teamJpaRepositoryHelper.findOne(id);
        Team team = jpaTeamToTeamMapper.apply(jpaTeam);
        return team;
    }

    @Override
    public Team findByExternalId(String externalId) {
        JpaTeam jpaTeam = teamJpaRepositoryHelper.findByExternalId(externalId);
        Team team = jpaTeamToTeamMapper.apply(jpaTeam);
        return team;

    }

    @Override
    public Team save(Team team) {
        JpaTeam jpaTeam = teamToJpaTeamMapper.apply(team);
        jpaTeam = teamJpaRepositoryHelper.save(jpaTeam);
        return jpaTeamToTeamMapper.apply(jpaTeam);
    }

    @Override
    public void remove(Team team) {
        teamJpaRepositoryHelper.delete(team.getId());
    }


}
