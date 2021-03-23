package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;

import java.util.Set;

public interface AdvertisementService extends GenericService<Advertisement, Long> {

    @Override
    Set<Advertisement> findAll();

    @Override
    Advertisement findById(Long aLong);

    @Override
    Advertisement save(Advertisement obj);

    @Override
    void delete(Advertisement obj);
}
