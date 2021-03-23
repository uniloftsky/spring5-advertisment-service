package com.uniloftsky.spingframework.spring5advertismentservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdsController {

    @GetMapping("/postAd")
    public String postAdFormInit() {
        return "postAd";
    }

}
