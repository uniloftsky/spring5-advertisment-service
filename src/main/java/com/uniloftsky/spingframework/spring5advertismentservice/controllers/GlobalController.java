package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.categories.CategoryAscComparatorByName;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.cities.CityAscComparatorByName;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.regions.RegionAscComparatorByName;
import com.uniloftsky.spingframework.spring5advertismentservice.exceptions.NotFoundException;
import com.uniloftsky.spingframework.spring5advertismentservice.model.*;
import com.uniloftsky.spingframework.spring5advertismentservice.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Comparator;
import java.util.Set;

@ControllerAdvice
public class GlobalController {

    private final CategoryService categoryService;
    private final RegionService regionService;
    private final CityService cityService;
    private final AdvertisementService advertisementService;
    private final UserService userService;
    private final Comparator<Category> categoryComparator = new CategoryAscComparatorByName();
    private final Comparator<City> cityComparator = new CityAscComparatorByName();
    private final Comparator<Region> regionComparator = new RegionAscComparatorByName();

    public GlobalController(CategoryService categoryService, RegionService regionService, CityService cityService, AdvertisementService advertisementService, UserService userService) {
        this.categoryService = categoryService;
        this.regionService = regionService;
        this.cityService = cityService;
        this.advertisementService = advertisementService;
        this.userService = userService;
    }

    @ModelAttribute("categories")
    public Set<Category> getCategoriesList() {
        return categoryService.getSortedCategories(categoryComparator);
    }

    @ModelAttribute("regions")
    public Set<Region> getRegionsList() {
        return regionService.getSortedRegions(regionComparator);
    }

    @ModelAttribute("cities")
    public Set<City> getCitiesList() {
        return cityService.getSortedCities(cityComparator);
    }

    @ModelAttribute("activeAds")
    public Set<Advertisement> getActiveAds() {
        return advertisementService.findAllActive();
    }

    @ModelAttribute("allAds")
    public Set<Advertisement> getAllAds() {
        return advertisementService.findAll();
    }

    @ModelAttribute("users")
    public Set<User> getAllUsers() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Exception exception, Model model){
        model.addAttribute("exception", exception);
        return "error/404error";
    }

}
