package com.uniloftsky.spingframework.spring5advertismentservice.bootstrap;

import com.uniloftsky.spingframework.spring5advertismentservice.model.*;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.AdvertisementRepository;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.CategoryRepository;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.CityRepository;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.RegionRepository;
import com.uniloftsky.spingframework.spring5advertismentservice.services.RegionService;
import com.uniloftsky.spingframework.spring5advertismentservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final RegionService regionService;
    private final CityRepository cityRepository;
    private final AdvertisementRepository advertisementRepository;

    public DataLoader(UserService userService, CategoryRepository categoryRepository, RegionRepository regionRepository, RegionService regionService, CityRepository cityRepository, AdvertisementRepository advertisementRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.regionRepository = regionRepository;
        this.regionService = regionService;
        this.cityRepository = cityRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = loadUsers();
        List<Category> categories = loadCategories();
        List<Region> regions = loadRegions();
        regionRepository.saveAll(regions);
        List<City> cities = loadCities();
        cityRepository.saveAll(cities);
        categoryRepository.saveAll(categories);

        List<Advertisement> ads = new ArrayList<>();
        ads.add(new Advertisement("заголовок", cities.get(0), categories.get(0), "desc", "respon", "qual", "offer", LocalDate.now(), "salary", "job natural", Status.CHECK, users.get(0)));
        advertisementRepository.saveAll(ads);
    }

    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("user", "123456", "test@mail.com", "Google Inc.", "desc", "website", "location"));
        userService.save(users.get(0));
        return users;
    }

    private List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Категорія"));
        categories.add(new Category("Категорія1"));
        categories.add(new Category("Категорія2"));
        return categories;
    }

    private List<Region> loadRegions() {
        List<Region> regions = new ArrayList<>();
        regions.add(new Region("Житомирська"));
        regions.add(new Region("Київська"));
        regions.add(new Region("Львівська"));
        return regions;
    }

    private List<City> loadCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Бердичів", regionService.findById(1L)));
        cities.add(new City("Київ", regionService.findById(2L)));
        cities.add(new City("Львів", regionService.findById(3L)));
        return cities;
    }

}
