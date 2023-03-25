package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.PetImagemRepository;
import com.belval.adocaoanimais.repository.PostagemRepository;
import com.belval.adocaoanimais.repository.RacaRepository;

@Controller
public class HomeController {
	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private RacaRepository racaRepository;
	@Autowired
	private CorRepository corRepository;
	@Autowired
	private PetImagemRepository petImagemRepository;

	@GetMapping("/pet/home")
	public String list(Model model) {
		model.addAttribute("postagem", postagemRepository.findAllAtivas());
		System.out.println("p é -> " + postagemRepository.findAllAtivas());
		return "home";
	}

	@GetMapping("/pet/public/galeria")
	public ModelAndView index() {
		List<Animal> animais = this.animalRepository.findAllAtivas();
		ModelAndView mv = new ModelAndView("galeria/index");
		mv.addObject("animais", animais);
		return mv;
	}

	@GetMapping("/pet/public/galeria/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();
			ModelAndView mv = new ModelAndView("galeria/show");
			mv.addObject(animal);
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			System.out.println("$$$$$$$$$$$ CERTO");
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
			return index();
		}
	}

}
