package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.AdvertisementRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserService userService;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, UserService userService) {
        this.advertisementRepository = advertisementRepository;
        this.userService = userService;
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
        return advertisementRepository.save(obj);
    }

    @Override
    public Advertisement save(Advertisement obj, Authentication authentication) {
        obj.setPublicDate(LocalDate.now());
        obj.setUser(userService.findByUsername(authentication.getName()));
        return advertisementRepository.save(obj);
    }

    @Override
    public void delete(Advertisement obj) {
        advertisementRepository.delete(obj);
    }
}
