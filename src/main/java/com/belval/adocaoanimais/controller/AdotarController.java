package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdotarController {

    @GetMapping("/pet/intencao-adotar")
    public String novo() {
        return "intencao-adotar";
    }

    
    
}
