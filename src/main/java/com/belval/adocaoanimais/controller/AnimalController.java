package com.belval.adocaoanimais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/new")
	public ModelAndView nnew( RequisicaoFormAnimal requisicao ) { // trabalhar na requisição              RequisicaoFormAnimal requisicao
		ModelAndView mv = new ModelAndView("private/animal/new");
		List<PetRaca> racas = racaRepository.findAll();
		mv.addObject("listaRaca",racas);
		List<PetCor> cores = corRepository.findAll();
		mv.addObject("listaCor",cores);
		mv.addObject("listaEspecie", Especie.values());
		mv.addObject("listaPorte", Porte.values());
		return mv;
	}
		 

	@PostMapping("")
	public ModelAndView create( RequisicaoFormAnimal requisicao,BindingResult bindingResult ){
		System.out.println("#########################################################################CREATE");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			System.out.println("ERRO \n\n"+bindingResult+"\n\n");
			ModelAndView mv = new ModelAndView("private/animal/new");
			return mv;
		} else {
			Animal animal = requisicao.toAnimal();
			// animal.setAtivo(true);
			
			this.animalRepository.save(animal);
			// return new ModelAndView("redirect:/pet/home" + animal.getId());
			return new ModelAndView("redirect:/pet/home" );
		}
	}

	// @PostMapping("/pet/cadastroAnimal")
	// public ModelAndView salvar(Animal animal) {
	// 	ModelAndView mv = new ModelAndView("redirect:../pet/home");

	// 	if (!animal.getNome().isEmpty()) {
	// 		animal.setUserId(1);
	// 		repository.save(animal);
	// 	}
	// 	return mv;
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
