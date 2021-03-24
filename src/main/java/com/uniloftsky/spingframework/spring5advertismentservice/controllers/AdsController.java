package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.services.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdsController {

    private final AdvertisementService advertisementService;

    public AdsController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/postAd")
    public String postAdFormInit(Model model) {
        model.addAttribute("ad", new Advertisement());
        return "postAd";
    }

    @PostMapping("/postAd")
    public String postAdFormProcess(@ModelAttribute("ad") Advertisement advertisement) {
        advertisementService.save(advertisement);
        return "redirect:/catalog";
    }

    @GetMapping({"/catalog", "catalog"})
    public String getCatalogPage() {
        return "catalog";
    }

}
