package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Region;

import java.util.Set;

public interface RegionService extends GenericService<Region, Long> {

    @Override
    Set<Region> findAll();

    @Override
    Region findById(Long aLong);

    @Override
    Region save(Region obj);

    @Override
    void delete(Region obj);
}
