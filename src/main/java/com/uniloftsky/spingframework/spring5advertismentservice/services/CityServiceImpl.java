package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.exceptions.NotFoundException;
import com.uniloftsky.spingframework.spring5advertismentservice.model.City;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Set<City> findAll() {
        Set<City> cities = new HashSet<>();
        cityRepository.findAll().iterator().forEachRemaining(cities::add);
        return cities;
    }

    @Override
    public TreeSet<City> getSortedCities(Comparator<City> comparator) {
        TreeSet<City> cities = new TreeSet<>(comparator);
        cityRepository.findAll().iterator().forEachRemaining(cities::add);
        return cities;
    }

    @Override
    public City findById(Long aLong) {
        Optional<City> cityOptional = cityRepository.findById(aLong);
        if (cityOptional.isEmpty()) {
            throw new NotFoundException("Місто з заданим ID не знайдено");
        }
        return cityOptional.get();
    }

    @Override
    public City save(City obj) {
        return cityRepository.save(obj);
    }

    @Override
    public void delete(City obj) {
        cityRepository.delete(obj);
    }
}
