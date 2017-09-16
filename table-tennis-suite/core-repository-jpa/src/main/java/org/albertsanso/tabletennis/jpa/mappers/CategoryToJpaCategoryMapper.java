package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaCategory;
import org.albertsanso.tabletennis.jpa.model.JpaCompetition;
import org.albertsanso.tabletennis.model.Category;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class CategoryToJpaCategoryMapper implements Function<Category, JpaCategory> {

    private CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper;

    @Inject
    public CategoryToJpaCategoryMapper(CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper) {
        this.competitionToJpaCompetitionMapper = competitionToJpaCompetitionMapper;
    }

    @Override
    public JpaCategory apply(Category category) {

        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(category.getCompetition());
        JpaCategory jpaCategory = new JpaCategory(category.getCodeName(), category.getName(), category.getSeason().seasonKey);
        jpaCategory.setCompetition(jpaCompetition);
        jpaCategory.setExternalId(category.getExternalId());
        return jpaCategory;
    }
}
