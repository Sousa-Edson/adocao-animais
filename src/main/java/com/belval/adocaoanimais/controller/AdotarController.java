package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.auxiliar.Menu;
import com.belval.adocaoanimais.dto.RequisicaoFormAdotar;
import com.belval.adocaoanimais.model.Adotar;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.AdotarRepository;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.PetImagemRepository;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
@RequestMapping(value = "/pet/private/intencao-adotar")
@PreAuthorize("hasAnyAuthority('ADMIN','COLLABORATOR','SUPPORT','USER')")
public class AdotarController {
	@Autowired
	private AdotarRepository adotarRepository;
	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private PetImagemRepository petImagemRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	Menu menu = new Menu();

 
	@GetMapping("")
	public ModelAndView index(Authentication authentication) {
		Usuario user = null;
		try {
			user = usuarioRepository.findByEmail(authentication.getName());
		} catch (Exception e) {
		}
		menu.setTitulo("Minhas solicitações de adoção (animal)");
		menu.setSelecao("intencao");
		List<Adotar> animais = this.adotarRepository.findByUserId(user.getId());
		ModelAndView mv = new ModelAndView("intencao/index");
		mv.addObject("animais", animais);
		mv.addObject("menu", menu);
		return mv;
	}

	@GetMapping("/new/{id}")
	public ModelAndView nnew(@PathVariable Long id, RequisicaoFormAdotar requisicao) {
		List<Animal> animais = this.animalRepository.findAll();
		ModelAndView mv = new ModelAndView("intencao/new");
		mv.addObject("petId", id);
		mv.addObject("animais", animais);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Adotar> optional = this.adotarRepository.findById(id);
		System.out.println("*************************\n\n\n\n" + optional.get().getId());
		// Optional<Animal> optional =
		// this.animalRepository.findById(optionalAdotar.get().getAnimal().getId());
		// System.out.println("*************************\n\n\n"+optional.get());
		if (optional.isPresent()) {
			Animal animal = optional.get().getAnimal();
			ModelAndView mv = new ModelAndView("intencao/show");
			mv.addObject(optional.get());
			mv.addObject(animal);
//			mv.addObject(animal);
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
			return null;
		}
	}

	@PostMapping("/{id}")
	public ModelAndView create(@PathVariable Long id, @Valid RequisicaoFormAdotar requisicao,
			BindingResult bindingResult, Authentication authentication) {
		System.out.println("Salvando");
		Usuario user = null;
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			System.out.println("ERRO \n\n" + bindingResult + "\n\n");
			ModelAndView mv = new ModelAndView("intencao/new");
			mv.addObject("petId", id);
			return mv;
		} else {
			try {
				user = usuarioRepository.findByEmail(authentication.getName());
				System.out.println("ID: " + user.getId());

			} catch (Exception e) {
			}
			Adotar adotar = requisicao.toAdotar();
			adotar.setAtivo(true);
			adotar.setUserId(user.getId());
			Optional<Animal> optionalAnimal = animalRepository.findById(id);
			adotar.setAnimal(optionalAnimal.get());
			this.adotarRepository.save(adotar);
			System.out.println("salvo");
			return new ModelAndView("redirect:/pet/private/intencao-adotar");
		}

		// return new ModelAndView("redirect:/pet/home" + animal.getId());

	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id, Model model, ModelMap m) {

		menu.setTitulo("Minhas solicitações de adoção (animal)");
		menu.setSelecao("intencao");

		try {
			Optional<Animal> e = animalRepository.findById(id);
			if (e == null) {
				System.out.println("555555555555555555555555555555555");
			} else {
				List<Adotar> animais = this.adotarRepository.findAll();
				model.addAttribute("animais", animais);
				model.addAttribute("menu", menu);
				model.addAttribute("animalId", e.get().getId());
				m.addAttribute("exc", true);
				model.addAttribute("menu", menu);
			}
		} catch (Exception e) {
			System.err.println("\n\n\n#########################\n\nErro do try cath - delete\n\n" + e
					+ "\n\n################################");
		}
		return "intencao/index";
	}

	@GetMapping("/{id}/destroy")
	public String destroy(@PathVariable("id") Long id, Model model) {
		try {

			adotarRepository.deleteById(id);
		} catch (Exception e) {
			System.err.println("\n\n\n#########################\n\nErro do try cath - destroy\n\n" + e
					+ "\n\n################################");
		}
		return "redirect:/pet/private/intencao-adotar";
	}

}
