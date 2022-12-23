package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/pet/dashboard")
    public String novo() {
        return "dashboard";
    }

     
}
