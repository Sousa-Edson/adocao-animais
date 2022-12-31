package com.belval.adocaoanimais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.repository.AnimalRepository;

@Controller
@RequestMapping(value = "/pet/private/intencao-adotar")
public class AdotarController {
    @Autowired
	private AnimalRepository animalRepository;

  

    @GetMapping("")
	public ModelAndView index() {
		List<Animal> animais = this.animalRepository.findAll();
		ModelAndView mv = new ModelAndView("private/intencao/index");
		mv.addObject("animais", animais);
		return mv;
	}
    @GetMapping("/new")
	public ModelAndView nnew() {
		List<Animal> animais = this.animalRepository.findAll();
		ModelAndView mv = new ModelAndView("private/intencao/new");
		mv.addObject("animais", animais);
		return mv;
	}

    
    
}
