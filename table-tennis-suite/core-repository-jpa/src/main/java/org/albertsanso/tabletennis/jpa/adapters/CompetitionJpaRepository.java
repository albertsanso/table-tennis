package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.CompetitionToJpaCompetitionMapper;
import org.albertsanso.tabletennis.jpa.mappers.JpaCompetitionToCompetitionMapper;
import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.albertsanso.tabletennis.jpa.repository.CompetitionJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Competition;
import org.albertsanso.tabletennis.port.CompetitionRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CompetitionJpaRepository implements CompetitionRepository {

    private CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper;
    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;
    private CompetitionJpaRepositoryHelper competitionJpaRepositoryHelper;

    @Inject
    public CompetitionJpaRepository(CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper, JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper, CompetitionJpaRepositoryHelper competitionJpaRepositoryHelper) {
        this.competitionToJpaCompetitionMapper = competitionToJpaCompetitionMapper;
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
        this.competitionJpaRepositoryHelper = competitionJpaRepositoryHelper;
    }

    @Override
    public Competition save(Competition competition) {
        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(competition);
        jpaCompetition = competitionJpaRepositoryHelper.save(jpaCompetition);
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }

    @Override
    public void remove(Competition competition) {
        competitionJpaRepositoryHelper.delete(competition.getId());
    }

    @Override
    public Competition findById(Long id) {
        JpaCompetition jpaCompetition = competitionJpaRepositoryHelper.findOne(id);
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }
}
