package com.uniloftsky.spingframework.spring5advertismentservice.comparators.categories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;

import java.util.Comparator;

public class CategoryAscComparatorById implements Comparator<Category> {

    @Override
    public int compare(Category o1, Category o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
