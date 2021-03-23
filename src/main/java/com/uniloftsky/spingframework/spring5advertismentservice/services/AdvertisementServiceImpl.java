package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Set<Advertisement> findAll() {
        Set<Advertisement> advertisements = new HashSet<>();
        advertisementRepository.findAll().iterator().forEachRemaining(advertisements::add);
        return advertisements;
    }

    @Override
    public Advertisement findById(Long aLong) {
        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(aLong);
        if (advertisementOptional.isEmpty()) {
            throw new RuntimeException("Expected ad not found!");
        }
        return advertisementOptional.get();
    }

    @Override
    public Advertisement save(Advertisement obj) {
        return null;
    }

    @Override
    public void delete(Advertisement obj) {

    }
}
