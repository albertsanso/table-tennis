package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.jpa.model.JpaCategory;
import org.albertsanso.tabletennis.model.Category;
import org.albertsanso.tabletennis.model.Competition;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaCategoryToCategoryMapper implements Function<JpaCategory, Category> {

    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;

    @Inject
    public JpaCategoryToCategoryMapper(JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper) {
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
    }

    @Override
    public Category apply(JpaCategory jpaCategory) {
        if (jpaCategory == null) return null;

        Competition competition = jpaCompetitionToCompetitionMapper.apply(jpaCategory.getCompetition());

        Season season = Season.getByKey(jpaCategory.getSeason());

        Category.CategoryBuilder builder = new Category.CategoryBuilder(
                jpaCategory.getCodeName(),
                jpaCategory.getName(),
                season
        );

        Category category = builder
                .withCompetition(competition)
                .withExternalId(jpaCategory.getExternalId())
                .create();

        return category;
    }
}
