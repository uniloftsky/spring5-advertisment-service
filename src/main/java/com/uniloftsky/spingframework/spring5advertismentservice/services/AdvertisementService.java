package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import org.springframework.security.core.Authentication;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public interface AdvertisementService extends GenericService<Advertisement, Long> {

    @Override
    Set<Advertisement> findAll();

    TreeSet<Advertisement> getLastAds(Comparator<Advertisement> comparator, int count);

    TreeSet<Advertisement> findAllSortedById(Comparator<Advertisement> comparator);

    @Override
    Advertisement findById(Long aLong);

    @Override
    Advertisement save(Advertisement obj);

    Advertisement save(Advertisement obj, Authentication authentication);

    @Override
    void delete(Advertisement obj);
}
