package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile", params = "user")
    public String showProfile(@RequestParam("user") String login, Model model) {
        if(login.equals("anonymousUser")) {
            return "security/login_form";
        }
        model.addAttribute("user", userService.findByUsername(login));
        return "companyProfile";
    }

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "security/login_form";
        } else {
            return "redirect:/profile?user=" + authentication.getName();
        }
    }
}
