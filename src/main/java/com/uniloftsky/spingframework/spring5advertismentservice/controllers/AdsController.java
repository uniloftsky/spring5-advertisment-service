package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.services.AdvertisementService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String postAdFormProcess(@Valid @ModelAttribute("ad") Advertisement advertisement, BindingResult result, Authentication authentication) {
        if(result.hasErrors()) {
            return "postAd";
        }
        advertisementService.save(advertisement, authentication);
        return "redirect:/catalog";
    }

    @GetMapping({"/catalog", "catalog"})
    public String getCatalogPage(Model model) {
        model.addAttribute("ads", advertisementService.findAll());
        return "catalog";
    }

    @GetMapping(value = "/ad", params = "id")
    public String getAdPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("ad", advertisementService.findById(id));
        return "item";
    }

}
