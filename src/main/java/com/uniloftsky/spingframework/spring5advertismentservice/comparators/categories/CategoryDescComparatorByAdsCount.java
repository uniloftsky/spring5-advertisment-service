package com.uniloftsky.spingframework.spring5advertismentservice.comparators.categories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;

import java.util.Comparator;

public class CategoryDescComparatorByAdsCount implements Comparator<Category> {

    @Override
    public int compare(Category o1, Category o2) {
        Integer o1Size = o1.getAds().size();
        Integer o2Size = o2.getAds().size();
        return (o2Size < o1Size) ? -1 : ((o1Size == o2Size) ? 1 : 1);
    }
}
