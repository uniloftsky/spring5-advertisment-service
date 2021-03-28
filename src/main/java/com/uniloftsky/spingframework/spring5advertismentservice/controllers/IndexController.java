package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads.AdDescComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.categories.CategoryAscComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;
import com.uniloftsky.spingframework.spring5advertismentservice.services.AdvertisementService;
import com.uniloftsky.spingframework.spring5advertismentservice.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.Collectors.toCollection;

@Controller
public class IndexController {

    private final AdvertisementService advertisementService;
    private final CategoryService categoryService;
    private final Comparator<Advertisement> descComparatorById = new AdDescComparatorById();

    public IndexController(AdvertisementService advertisementService, CategoryService categoryService) {
        this.advertisementService = advertisementService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/", "index", "/index", "*", "/*", "*.html"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping({"/item", "item"})
    public String getItemPage() {
        return "item";
    }

    @GetMapping({"/companyProfile", "companyProfile"})
    public String getCompanyProfilePage() {
        return "companyProfile";
    }

    @ModelAttribute("lastJobs")
    public Set<Advertisement> getLastJobs() {
        return advertisementService.getLastAds(descComparatorById, 6);
    }

    @ModelAttribute("categoryMap")
    public Map<Category, Integer> getCategoryMap() {
        return categoryService.getCategoriesCount();
    }

    //bad
    @ModelAttribute("categoriesFooter")
    public TreeSet<Category> getCategoriesFooter() {
        return categoryService.getSortedCategories(new CategoryAscComparatorById()).stream().limit(4).collect(toCollection(() -> new TreeSet<>(new CategoryAscComparatorById())));
    }

    @GetMapping("/forbidden")
    public String getForbiddenPage() {
        return "error/403error";
    }

}
