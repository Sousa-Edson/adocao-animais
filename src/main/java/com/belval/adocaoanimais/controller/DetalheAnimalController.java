package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DetalheAnimalController {

    @GetMapping("/pet/detalhe-animal")
    public String novo() {
        return "detalhe-animal";
    }

   
}
