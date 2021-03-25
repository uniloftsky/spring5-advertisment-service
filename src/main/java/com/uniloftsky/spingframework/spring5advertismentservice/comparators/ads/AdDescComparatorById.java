package com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;

import java.util.Comparator;

public class AdDescComparatorById implements Comparator<Advertisement> {

    @Override
    public int compare(Advertisement o1, Advertisement o2) {
        return o2.getId().compareTo(o1.getId());
    }
}
