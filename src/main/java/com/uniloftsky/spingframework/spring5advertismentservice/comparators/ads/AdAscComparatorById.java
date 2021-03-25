package com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;

import java.util.Comparator;

public class AdAscComparatorById implements Comparator<Advertisement> {

    @Override
    public int compare(Advertisement o1, Advertisement o2) {
        return o1.getId().compareTo(o2.getId());
    }

}
