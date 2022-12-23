package com.belval.adocaoanimais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.repository.RacaRepository;

@Controller
@RequestMapping(value = "/pet/admin/pet-raca")
public class RacaController {
	@Autowired
	private RacaRepository racaRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<PetRaca> racas = this.racaRepository.findAll();
		ModelAndView mv = new ModelAndView("admin/raca/index");
		mv.addObject("racas", racas);
		return mv;
	}


	// @PostMapping("/pet/pet-raca")
	// public ModelAndView novo(PetRaca raca) {

	// 	ModelAndView mv = new ModelAndView("redirect:../pet/pet-raca");

	// 	if (!raca.getRaca().isEmpty()) {
	// 		repository.save(raca);
	// 	}

	// 	return mv;
	// }

	// @GetMapping("/pet/pet-raca")
	// public String list(Model model) {

	// 	model.addAttribute("c", new PetRaca());
	// 	model.addAttribute("raca", repository.findAll());
	// 	return "animal/pet-raca";
	// }

	// @GetMapping("/pet/pet-raca/{id}/edit")
	// public String edit(@PathVariable("id") int id, Model model, ModelMap m) {
	// 	PetRaca c = repository.findById(id);
	// 	if (c == null) {
	// 		return "cor-nao-encontrada";
	// 	} else {
	// 		model.addAttribute("raca", repository.findAll());
	// 		model.addAttribute("c", c);
	// 		m.addAttribute("msg", "mensagem");
	// 		return "animal/pet-raca";
	// 	}

	// }

	// @GetMapping("/pet/pet-raca/{id}/excluir")
	// public String excluir(@PathVariable("id") int id, Model model, ModelMap m) {
	// 	// repository.deleteById(id);
	// 	PetRaca e = repository.findById(id);
	// 	if (e == null) {
	// 		return list(model);
	// 	} else {
	// 		System.out.println(e.getId());
	// 		model.addAttribute("raca", repository.findAll());
	// 		model.addAttribute("c", new PetRaca());
	// 		model.addAttribute("e", e);
	// 		m.addAttribute("exc", true);
	// 		return "animal/pet-raca";
	// 	}
	// }

	// @GetMapping("/pet/pet-raca/{id}/excluindo")
	// public String excluirConfirmado(@PathVariable("id") int id, Model model) {
	// 	PetRaca e = repository.findById(id);
	// 	if (e == null) {
	// 		return list(model);
	// 	} else {
	// 		repository.deleteById(id);
	// 		return list(model);
	// 	}

	// }

}
