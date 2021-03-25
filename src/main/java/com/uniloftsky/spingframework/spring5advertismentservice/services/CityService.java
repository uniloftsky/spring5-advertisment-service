package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.City;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public interface CityService extends GenericService<City, Long> {

    @Override
    Set<City> findAll();

    TreeSet<City> getSortedCities(Comparator<City> comparator);

    @Override
    City findById(Long aLong);

    @Override
    City save(City obj);

    @Override
    void delete(City obj);
}
