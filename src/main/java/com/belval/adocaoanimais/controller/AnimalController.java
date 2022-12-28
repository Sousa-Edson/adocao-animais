package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.dto.RequisicaoFormAnimal;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.RacaRepository;
import com.belval.enums.Especie;
import com.belval.enums.Porte;

@Controller
@RequestMapping(value = "/pet/private/animal")
public class AnimalController {
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private RacaRepository racaRepository;
	@Autowired
	private CorRepository corRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<Animal> animais = this.animalRepository.findAll();
		ModelAndView mv = new ModelAndView("private/animal/index");
		mv.addObject("animais", animais);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormAnimal requisicao) { // trabalhar na requisição RequisicaoFormAnimal
																// requisicao
		ModelAndView mv = new ModelAndView("private/animal/new");
		List<PetRaca> racas = racaRepository.findAll();
		mv.addObject("listaRaca", racas);
		List<PetCor> cores = corRepository.findAll();
		mv.addObject("listaCor", cores);
		mv.addObject("listaEspecie", Especie.values());
		mv.addObject("listaPorte", Porte.values());
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormAnimal requisicao, BindingResult bindingResult) {
		System.out.println("#########################################################################CREATE");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			System.out.println("ERRO \n\n" + bindingResult + "\n\n");
			List<PetRaca> racas = racaRepository.findAll();
			ModelAndView mv = new ModelAndView("private/animal/new");
			mv.addObject("listaRaca", racas);
			List<PetCor> cores = corRepository.findAll();
			mv.addObject("listaCor", cores);
			mv.addObject("listaEspecie", Especie.values());
			mv.addObject("listaPorte", Porte.values());
			return mv;
		} else {
			Animal animal = requisicao.toAnimal();
			// animal.setAtivo(true);
			animal.setDisponivel(true);
			this.animalRepository.save(animal);
			// return new ModelAndView("redirect:/pet/home" + animal.getId());
			return new ModelAndView("redirect:/pet/private/animal/new/" + animal.getId());
		}
	}

	@GetMapping("/new/{id}")
	public ModelAndView nNewImage(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();
			ModelAndView mv = new ModelAndView("private/animal/new-image");
			mv.addObject(animal);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou a ");
		}
		System.out.println("\n\n\n#######################Erro");
		return new ModelAndView("redirect:/pet/private/animal/");
	}

	@GetMapping("/{id}/activate")
	public String activate(@PathVariable("id") Long id, Model model, RequisicaoFormAnimal requisicao) {
		Optional<Animal> e = animalRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Animal> optional = this.animalRepository.findById(id);
			if (optional.isPresent()) {
				Animal animal = requisicao.toAnimalCheck(optional.get());
				animal.setId(id);
				animal.setDisponivel(true);
				this.animalRepository.save(animal);
			} else {
				System.out.println("########### Não achou o animal");
			}
			model.addAttribute("animais", animalRepository.findAll());
		}
		return "redirect:/pet/private/animal";
	}

	@GetMapping("/{id}/deactivate")
	public String deactivate(@PathVariable("id") Long id, Model model, RequisicaoFormAnimal requisicao) {
		Optional<Animal> e = animalRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Animal> optional = this.animalRepository.findById(id);
			if (optional.isPresent()) {
				Animal animal = requisicao.toAnimalCheck(optional.get());
				animal.setId(id);
				animal.setDisponivel(false);
				this.animalRepository.save(animal);
			} else {
				System.out.println("########### Não achou o animal");
			}
			model.addAttribute("animais", animalRepository.findAll());
		}
		return "redirect:/pet/private/animal";
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormAnimal requisicao) {
		Optional<Animal> optional = this.animalRepository.findById(id);

		if (optional.isPresent()) {
			ModelAndView mv = new ModelAndView("private/animal/edit");
			Animal animal = optional.get();
			requisicao.fromAnimal(animal);
			mv.addObject("animalId", animal.getId());
			List<PetRaca> racas = racaRepository.findAll();
			mv.addObject("listaRaca", racas);
			List<PetCor> cores = corRepository.findAll();
			mv.addObject("listaCor", cores);
			mv.addObject("listaEspecie", Especie.values());
			mv.addObject("listaPorte", Porte.values());
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou o animal");
			return new ModelAndView("redirect:/private/animal");
		}

	}

	// @PostMapping("/pet/cadastroAnimal")
	// public ModelAndView salvar(Animal animal) {
	// ModelAndView mv = new ModelAndView("redirect:../pet/home");

	// if (!animal.getNome().isEmpty()) {
	// animal.setUserId(1);
	// repository.save(animal);
	// }
	// return mv;
	// }

	/*
	 * private static List<Animal> listaPet = new ArrayList<Animal>(); private
	 * static int next = 1;
	 * 
	 * private Animal buscarPetPeloId(int id) { for (Animal p : listaPet) { if
	 * (p.getId() == id) { return p; } } return null; }
	 * 
	 * @GetMapping("/pet") public String novo(Model model) {
	 * model.addAttribute("pet", new Animal()); return "pet-form"; }
	 * 
	 * @PostMapping("/pet") public ModelAndView novo(Animal pet) {
	 * 
	 * ModelAndView mv = new ModelAndView("redirect:/list");
	 * 
	 * if (pet.getId() == 0) { pet.setId(next++); listaPet.add(pet); } else {
	 * updateAnimal(pet); } return mv; }
	 * 
	 * @GetMapping("/list") public String list(Model model) {
	 * model.addAttribute("pet", listaPet); return "pet-list"; }
	 * 
	 * @GetMapping("/pet/{id}") public String detalhe(@PathVariable("id") int id,
	 * Model model) { Animal p = buscarPetPeloId(id); if (p == null) { return
	 * "pet-nao-encontrado"; } model.addAttribute("pet", p); return "pet-detalhe"; }
	 * 
	 * @GetMapping("/pet/{id}/edit") public String edit(@PathVariable("id") int id,
	 * Model model) {
	 * 
	 * Animal p = buscarPetPeloId(id); if (p == null) { return "pet-nao-encontrado";
	 * } model.addAttribute("pet", p); return "pet-form"; }
	 * 
	 * private void updateAnimal(Animal pet) { ListIterator<Animal> i =
	 * listaPet.listIterator(); while (i.hasNext()) { Animal atual = i.next(); if
	 * (atual.getId() == pet.getId()) { i.set(pet); } } }
	 */

}
