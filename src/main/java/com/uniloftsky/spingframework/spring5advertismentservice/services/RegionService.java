package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Region;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public interface RegionService extends GenericService<Region, Long> {

    @Override
    Set<Region> findAll();

    TreeSet<Region> getSortedRegions(Comparator<Region> comparator);

    @Override
    Region findById(Long aLong);

    @Override
    Region save(Region obj);

    @Override
    void delete(Region obj);
}
