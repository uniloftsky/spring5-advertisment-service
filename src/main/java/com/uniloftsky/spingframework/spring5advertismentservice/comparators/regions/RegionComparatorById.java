package com.uniloftsky.spingframework.spring5advertismentservice.comparators.regions;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Region;

import java.util.Comparator;

public class RegionComparatorById implements Comparator<Region> {

    @Override
    public int compare(Region o1, Region o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
