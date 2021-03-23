package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;

import java.util.Set;

public interface CategoryService extends GenericService<Category, Long> {

    @Override
    Set<Category> findAll();

    @Override
    Category findById(Long aLong);

    @Override
    Category save(Category obj);

    @Override
    void delete(Category obj);
}
