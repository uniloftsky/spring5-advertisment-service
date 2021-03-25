package com.uniloftsky.spingframework.spring5advertismentservice.comparators.cities;

import com.uniloftsky.spingframework.spring5advertismentservice.model.City;

import java.util.Comparator;

public class CityAscComparatorById implements Comparator<City> {

    @Override
    public int compare(City o1, City o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
