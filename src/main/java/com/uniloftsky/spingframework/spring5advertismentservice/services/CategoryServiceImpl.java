package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public TreeSet<Category> getSortedCategories(Comparator<Category> comparator) {
        TreeSet<Category> categories = new TreeSet<>(comparator);
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public Category findById(Long aLong) {
        Optional<Category> categoryOptional = categoryRepository.findById(aLong);
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found!");
        }
        return categoryOptional.get();
    }

    @Override
    public Category save(Category obj) {
        return categoryRepository.save(obj);
    }

    @Override
    public void delete(Category obj) {
        categoryRepository.delete(obj);
    }
}
