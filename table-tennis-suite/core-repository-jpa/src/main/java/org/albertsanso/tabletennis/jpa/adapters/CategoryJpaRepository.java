package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.CategoryToJpaCategoryMapper;
import org.albertsanso.tabletennis.jpa.mappers.JpaCategoryToCategoryMapper;
import org.albertsanso.tabletennis.jpa.model.JpaCategory;
import org.albertsanso.tabletennis.jpa.repository.CategoryJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Category;
import org.albertsanso.tabletennis.port.CategoryRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CategoryJpaRepository implements CategoryRepository {

    private CategoryToJpaCategoryMapper categoryToJpaCategoryMapper;
    private JpaCategoryToCategoryMapper jpaCategoryToCategoryMapper;
    private CategoryJpaRepositoryHelper categoryJpaRepositoryHelper;

    @Inject
    public CategoryJpaRepository(CategoryToJpaCategoryMapper categoryToJpaCategoryMapper, JpaCategoryToCategoryMapper jpaCategoryToCategoryMapper, CategoryJpaRepositoryHelper categoryJpaRepositoryHelper) {
        this.categoryToJpaCategoryMapper = categoryToJpaCategoryMapper;
        this.jpaCategoryToCategoryMapper = jpaCategoryToCategoryMapper;
        this.categoryJpaRepositoryHelper = categoryJpaRepositoryHelper;
    }

    @Override
    public Category save(Category category) {
        JpaCategory jpaCategory = categoryToJpaCategoryMapper.apply(category);
        jpaCategory = categoryJpaRepositoryHelper.save(jpaCategory);
        return jpaCategoryToCategoryMapper.apply(jpaCategory);
    }

    @Override
    public void remove(Category category) {
        categoryJpaRepositoryHelper.delete(category.getId());
    }

    @Override
    public Category findById(Long id) {
        JpaCategory jpaCategory = categoryJpaRepositoryHelper.findOne(id);
        Category category = jpaCategoryToCategoryMapper.apply(jpaCategory);
        return category;
    }

    @Override
    public Category findByExternalId(String externalId) {
        JpaCategory jpaCategory = categoryJpaRepositoryHelper.findByExternalId(externalId);
        Category category = jpaCategoryToCategoryMapper.apply(jpaCategory);
        return category;

    }
}
