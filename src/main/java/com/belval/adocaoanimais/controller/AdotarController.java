package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdotarController {

    @GetMapping("/pet/intencao-adotar")
    public String novo() {
        return "/catalogo/intencao-adotar";
    }

    
    
}
