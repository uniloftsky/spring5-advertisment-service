package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.services.AdvertisementService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final AdvertisementService advertisementService;

    public AdminController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/admin")
    public String getAdminPage(@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("checkAds", advertisementService.findAllCheckingPage(pageable));
        return "admin/index";

    }

}
