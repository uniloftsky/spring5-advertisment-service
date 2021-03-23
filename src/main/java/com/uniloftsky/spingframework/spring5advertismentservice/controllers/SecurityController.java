package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login_form";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "security/register_form";
    }

}
