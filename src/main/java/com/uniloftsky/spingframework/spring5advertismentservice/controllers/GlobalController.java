package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.categories.CategoryAscComparatorByName;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.cities.CityAscComparatorByName;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.regions.RegionAscComparatorByName;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;
import com.uniloftsky.spingframework.spring5advertismentservice.model.City;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Region;
import com.uniloftsky.spingframework.spring5advertismentservice.services.CategoryService;
import com.uniloftsky.spingframework.spring5advertismentservice.services.CityService;
import com.uniloftsky.spingframework.spring5advertismentservice.services.RegionService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.Set;

@ControllerAdvice
public class GlobalController {

    private final CategoryService categoryService;
    private final RegionService regionService;
    private final CityService cityService;
    private final Comparator<Category> categoryComparator = new CategoryAscComparatorByName();
    private final Comparator<City> cityComparator = new CityAscComparatorByName();
    private final Comparator<Region> regionComparator = new RegionAscComparatorByName();

    public GlobalController(CategoryService categoryService, RegionService regionService, CityService cityService) {
        this.categoryService = categoryService;
        this.regionService = regionService;
        this.cityService = cityService;
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

}
