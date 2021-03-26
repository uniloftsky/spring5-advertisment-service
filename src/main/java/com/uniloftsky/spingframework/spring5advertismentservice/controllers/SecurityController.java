package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.UserRepository;
import com.uniloftsky.spingframework.spring5advertismentservice.services.UserService;
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
import java.util.Optional;

@Controller
public class SecurityController {

    private final UserService userService;
    private final UserRepository userRepository;

    public SecurityController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login_form";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("loginError", false);
        return "security/register_form";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("profileImage") MultipartFile file, Model model) throws IOException {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (!user.getUsername().isEmpty() && userOptional.isPresent()) {
            if (userService.findByUsername(user.getUsername()) != null && user.getUsername().equals(userService.findByUsername(user.getUsername()).getUsername())) {
                model.addAttribute("loginError", true);
                return "security/register_form";
            }
        }
        if ((result.hasErrors() && file.isEmpty()) || file.isEmpty() || result.hasErrors()) {
            model.addAttribute("fileError", true);
            model.addAttribute("loginError", false);
            return "security/register_form";
        }
        userService.save(user, file);
        model.addAttribute("registered", true);
        return "security/login_form";
    }

}
