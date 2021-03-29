package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads.AdAscComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.services.AdvertisementService;
import com.uniloftsky.spingframework.spring5advertismentservice.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    public UserController(UserService userService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    @GetMapping(value = "/profile", params = "user")
    public String showProfile(@RequestParam("user") String login, Model model) {
        if (login.equals("anonymousUser")) {
            return "security/login_form";
        }
        model.addAttribute("user", userService.findByUsername(login));
        model.addAttribute("passwordError", false);
        model.addAttribute("ads", advertisementService.findAllSortedByByUser(new AdAscComparatorById(), userService.findByUsername(login)));
        return "profile/companyProfile";
    }

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "security/login_form";
        } else {
            return "redirect:/profile?user=" + authentication.getName();
        }
    }

    @GetMapping(value = "/editProfile", params = "user")
    public String editProfileFormInit(@RequestParam("user") String login, Model model, Authentication authentication) {
        if (login.equals("anonymousUser") || !login.equals(authentication.getName())) {
            return "security/login_form";
        }
        model.addAttribute("user", userService.findByUsername(login));
        return "profile/editProfile";
    }

    @PostMapping("/editProfile")
    public String editProfileFormProcess(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("profileImage") MultipartFile file, Model model) throws IOException {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "profile/editProfile";
        }
        userService.save(user, file);
        return "redirect:/profile";
    }

    @GetMapping("/editProfile")
    public String showProfilePage(Authentication authentication) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "security/login_form";
        } else {
            return "redirect:/editProfile?user=" + authentication.getName();
        }
    }

    @GetMapping(value = "/deleteAd", params = "id")
    public String deleteUserAd(@RequestParam("id") Long id, Authentication authentication) {
        Advertisement ad = advertisementService.findById(id);
        if (authentication == null || !authentication.getName().equals(ad.getUser().getUsername())) {
            return "redirect:/";
        }
        advertisementService.delete(ad);
        return "redirect:/profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(Authentication authentication, @RequestParam("newPassword") String password, Model model) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/";
        }
        if(password.isEmpty()) {
            model.addAttribute("passwordError", true);
            model.addAttribute("user", userService.findByUsername(authentication.getName()));
            return "profile/companyProfile";
        }
        userService.changePassword(userService.findByUsername(authentication.getName()), password);
        return "redirect:/profile";
    }
}
