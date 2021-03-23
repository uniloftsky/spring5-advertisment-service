package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.City;

import java.util.Set;

public interface CityService extends GenericService<City, Long> {

    @Override
    Set<City> findAll();

    @Override
    City findById(Long aLong);

    @Override
    City save(City obj);

    @Override
    void delete(City obj);
}
