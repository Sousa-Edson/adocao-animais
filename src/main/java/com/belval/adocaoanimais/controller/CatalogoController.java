package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {


    @GetMapping("/pet/catalogo")
    public String novo() {
        return "/catalogo/catalogo";
    }
}
