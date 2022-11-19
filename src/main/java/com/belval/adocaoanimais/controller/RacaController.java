package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.repository.RacaRepository;

@Controller
public class RacaController {
	@Autowired
	private RacaRepository repository;

	@PostMapping("/pet/pet-raca")
	public ModelAndView novo(PetRaca raca) {

		ModelAndView mv = new ModelAndView("redirect:../pet/pet-raca");

		if (!raca.getRaca().isEmpty()) {
			repository.save(raca);
		}

		return mv;
	}

	@GetMapping("/pet/pet-raca")
	public String list(Model model) {

		model.addAttribute("c", new PetRaca());
		model.addAttribute("raca", repository.findAll());
		return "pet-raca";
	}

	@GetMapping("/pet/pet-raca/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model, ModelMap m) {
		PetRaca c = repository.findById(id);
		if (c == null) {
			return "cor-nao-encontrada";
		} else {
			model.addAttribute("raca", repository.findAll());
			model.addAttribute("c", c);
			m.addAttribute("msg", "mensagem");
			return "pet-raca";
		}

	}

	@GetMapping("/pet/pet-raca/{id}/excluir")
	public String excluir(@PathVariable("id") int id, Model model, ModelMap m) {
		// repository.deleteById(id);
		PetRaca e = repository.findById(id);
		if (e == null) {
			return list(model);
		} else {
			System.out.println(e.getId());
			model.addAttribute("raca", repository.findAll());
			model.addAttribute("c", new PetRaca());
			model.addAttribute("e", e);
			m.addAttribute("exc", true);
			return "pet-raca";
		}
	}

	@GetMapping("/pet/pet-raca/{id}/excluindo")
	public String excluirConfirmado(@PathVariable("id") int id, Model model) {
		PetRaca e = repository.findById(id);
		if (e == null) {
			return list(model);
		} else {
			repository.deleteById(id);
			return list(model);
		}

	}

}
