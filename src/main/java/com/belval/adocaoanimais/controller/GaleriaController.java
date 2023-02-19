package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.RacaRepository;

@Controller
@RequestMapping(value = "/pet/public/galeria")
public class GaleriaController {
    @Autowired
	private AnimalRepository animalRepository;
    @Autowired
	private RacaRepository racaRepository;
	@Autowired
	private CorRepository corRepository;

    @GetMapping("")
    public ModelAndView index() {
        List<Animal> animais = this.animalRepository.findAllAtivas();
        ModelAndView mv = new ModelAndView("public/galeria/index");
        mv.addObject("animais", animais);
        return mv;
    }

    @GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();
			ModelAndView mv = new ModelAndView("public/galeria/show"); 
			mv.addObject(animal);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
            return index();
		}
	}

}
