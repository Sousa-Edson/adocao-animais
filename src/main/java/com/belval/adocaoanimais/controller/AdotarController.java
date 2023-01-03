package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Adotar;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.repository.AdotarRepository;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.RacaRepository;

@Controller
@RequestMapping(value = "/pet/private/intencao-adotar")
public class AdotarController {
	@Autowired
	private AdotarRepository adotarRepository;
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private RacaRepository racaRepository;
	@Autowired
	private CorRepository corRepository;

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

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();
			ModelAndView mv = new ModelAndView("private/intencao/show");
			Optional<PetRaca> racas = racaRepository.findById(optional.get().getRaca());
			mv.addObject("listaRaca", racas.get());
			Optional<PetCor> cores = corRepository.findById(optional.get().getCor());
			mv.addObject("listaCor", cores.get());
			mv.addObject(animal);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
			return null;
		}
	}

	@PostMapping("")
	public ModelAndView create(Adotar adotar) {
		System.out.println("Salvando");
		adotar.setUserId((long) 12);
		adotar.setAnimalId((long) 2);
		adotar.setAtivo(true);
		adotar.setAtivo(true);
		this.adotarRepository.save(adotar);
		System.out.println("salvo");
		// return new ModelAndView("redirect:/pet/home" + animal.getId());
		return new ModelAndView("redirect:/pet/private/intencao-adotar");

	}

}
