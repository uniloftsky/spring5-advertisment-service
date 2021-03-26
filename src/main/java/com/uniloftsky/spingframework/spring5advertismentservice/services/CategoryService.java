package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public interface CategoryService extends GenericService<Category, Long> {

    @Override
    Set<Category> findAll();

    TreeSet<Category> getSortedCategories(Comparator<Category> comparator);

    Map<Category, Integer> getCategoriesCount();

    @Override
    Category findById(Long aLong);

    @Override
    Category save(Category obj);

    @Override
    void delete(Category obj);
}
