package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Region;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Set<Region> findAll() {
        Set<Region> regions = new HashSet<>();
        regionRepository.findAll().iterator().forEachRemaining(regions::add);
        return regions;
    }

    @Override
    public TreeSet<Region> getSortedRegions(Comparator<Region> comparator) {
        TreeSet<Region> regions = new TreeSet<>(comparator);
        regionRepository.findAll().iterator().forEachRemaining(regions::add);
        return regions;
    }

    @Override
    public Region findById(Long aLong) {
        Optional<Region> regionOptional = regionRepository.findById(aLong);
        if (regionOptional.isEmpty()) {
            throw new RuntimeException("Expected region not found!");
        }
        return regionOptional.get();
    }

    @Override
    public Region save(Region obj) {
        return regionRepository.save(obj);
    }

    @Override
    public void delete(Region obj) {
        regionRepository.delete(obj);
    }
}
