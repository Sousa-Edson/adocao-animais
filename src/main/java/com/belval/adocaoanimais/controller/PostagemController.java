package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostagemController {

    @GetMapping("/pet/postagem")
    public String lista() {
        return "postagem-lista";
    }

//    @PostMapping("/pet/postagem")
//    public String lista() {
//        return "pet/postagem-lista";
//    }
    @GetMapping("/pet/postagem-novo")
    public String novo() {
        return "postagem";
    }

    @PostMapping("/pet/postagem-novo")
    public String novo2() {
        return "postagem";
    }

    @GetMapping("/pet/postagem-detalhe")
    public String detalhe() {
        return "postagem-detalhe";
    }

    @PostMapping("/pet/postagem-detalhe")
    public String detalhe2() {
        return "postagem-detalhe";
    }
}
