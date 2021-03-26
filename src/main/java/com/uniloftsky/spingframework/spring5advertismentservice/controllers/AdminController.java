package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads.AdAscComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.user.UserAscComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.model.*;
import com.uniloftsky.spingframework.spring5advertismentservice.services.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminController {

    private final AdvertisementService advertisementService;
    private final CategoryService categoryService;
    private final RegionService regionService;
    private final CityService cityService;
    private final UserService userService;
    private final Comparator<Advertisement> comparator = new AdAscComparatorById();

    public AdminController(AdvertisementService advertisementService, CategoryService categoryService, RegionService regionService, CityService cityService, UserService userService) {
        this.advertisementService = advertisementService;
        this.categoryService = categoryService;
        this.regionService = regionService;
        this.cityService = cityService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAdminPage(@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<Advertisement> pages = advertisementService.findAllCheckingPage(pageable);
        Integer maxPage = pages.getTotalPages();
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("ads", pages);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, pages.getTotalPages()).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("currentPage", pages.getNumber());
        return "admin/index";
    }

    @GetMapping("/admin/category")
    public String getCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category";
    }

    @PostMapping("/categoryAdd")
    public String categoryAddFormProcess(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/category";
        }
        categoryService.save(category);
        model.addAttribute("success", true);
        return "admin/category";
    }

    @GetMapping("/admin/region")
    public String getRegionPage(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("region", new Region());
        return "admin/region";
    }

    @PostMapping("/regionAdd")
    public String regionAddFormProcess(@Valid @ModelAttribute("region") Region region, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("city", new City());
            return "admin/region";
        }
        model.addAttribute("success", true);
        model.addAttribute("cityNameError", false);
        model.addAttribute("cityRegionError", false);
        regionService.save(region);
        return "redirect:/admin/region";
    }

    @PostMapping("/cityAdd")
    public String cityAddFormProcess(@Valid @ModelAttribute("city") City city, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("region", new Region());
            return "admin/region";
        }
        model.addAttribute("success1", true);
        cityService.save(city);
        return "redirect:/admin/region";
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.findAllSortedBy(new UserAscComparatorById()));
        return "admin/user";
    }

    @GetMapping(value = "/userDetails", params = "id")
    public String getUserPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("activeAds", advertisementService.getStatusAdsByUser(Status.ACTIVE, userService.findById(id), comparator));
        model.addAttribute("checkAds", advertisementService.getStatusAdsByUser(Status.CHECK, userService.findById(id), comparator));
        model.addAttribute("blockAds", advertisementService.getStatusAdsByUser(Status.BLOCK, userService.findById(id), comparator));
        return "admin/userDetails";
    }

    @GetMapping(value = "/userDelete", params = "id")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(userService.findById(id));
        return "redirect:/admin/user";
    }
}
