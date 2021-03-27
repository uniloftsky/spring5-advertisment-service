package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.categories.CategoryDescComparatorByAdsCount;
import com.uniloftsky.spingframework.spring5advertismentservice.exceptions.NotFoundException;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Comparator<Category> comparator = new CategoryDescComparatorByAdsCount();

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

    /*
        создаем мапу <категория, количество> в отсортированном виде по количеству вакансий,
        после получаем категории в отсортированном таким же образом виде с репозитория в количестве первых 8 штук,
        перебирая каждую категорию, записываем категорию как ключ, а количество вакансий как значение
    */

    @Override
    public Map<Category, Integer> getCategoriesCount() {
        Map<Category, Integer> categoryIntegerMap = new TreeMap<>(comparator);
        Set<Category> categories = new TreeSet<>(comparator);

        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        for (Category category : categories.stream().limit(8).collect(toCollection(() -> new TreeSet<>(comparator)))) {
            categoryIntegerMap.put(category, category.getAds().size());
        }
        return categoryIntegerMap;
    }

    @Override
    public Category findById(Long aLong) {
        Optional<Category> categoryOptional = categoryRepository.findById(aLong);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Категорію з заданим ID не знайдено");
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
