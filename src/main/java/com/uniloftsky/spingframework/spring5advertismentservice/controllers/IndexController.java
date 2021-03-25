package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads.AdDescComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.services.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.Set;

@Controller
public class IndexController {

    private final AdvertisementService advertisementService;
    private final Comparator<Advertisement> descComparatorById = new AdDescComparatorById();

    public IndexController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
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

}
