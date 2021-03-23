package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "/index", "*", "/*", "*.html"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping({"/catalog", "catalog"})
    public String getCatalogPage() {
        return "catalog";
    }

    @GetMapping({"/item", "item"})
    public String getItemPage() {
        return "item";
    }

    @GetMapping({"/profile", "profile"})
    public String getProfilePage() {
        return "profile";
    }

    @GetMapping({"/companyProfile", "companyProfile"})
    public String getCompanyProfilePage() {
        return "companyProfile";
    }

}
