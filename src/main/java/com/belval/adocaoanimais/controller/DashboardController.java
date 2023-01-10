package com.belval.adocaoanimais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.auxiliar.Menu;
import com.belval.adocaoanimais.model.Adotar;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.repository.AdotarRepository;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
public class DashboardController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AdotarRepository adotarRepository;

    Menu menu = new Menu();

    @GetMapping("/pet/dashboard")
    public String novo() {
        return "dashboard";
    }

    /* ANIMAL */
    @GetMapping("/pet/admin/animal")
    public ModelAndView indexAnimalAll() {
        menu.setTitulo("Todos os anúncios de animais");
        menu.setSelecao("anuncioAll");
        List<Animal> animais = this.animalRepository.findAll();
        ModelAndView mv = new ModelAndView("private/animal/index");
        mv.addObject("animais", animais);
        mv.addObject("menu", menu);
        return mv;
    }

    /* INTENÇÃO DE ADOTAR */
    @GetMapping("/pet/admin/intencao-adotar")
    public ModelAndView index() {
        menu.setTitulo("Todas as solicitações de adotação");
        menu.setSelecao("intencaoAll");
        List<Adotar> animais = this.adotarRepository.findAll();
        ModelAndView mv = new ModelAndView("private/intencao/index");
        mv.addObject("animais", animais);
        mv.addObject("menu", menu);
        return mv;
    }

}
