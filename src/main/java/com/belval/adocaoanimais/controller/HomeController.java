package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
public class HomeController {
	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping("/pet/home")
	public String list(Model model) {
		model.addAttribute("postagem", postagemRepository.findAllAtivas());
		 System.out.println("p é -> "+postagemRepository.findAllAtivas());
		return "home";
	}
 


	// @GetMapping("/")
	// public String home(Model model) {
	// 	model.addAttribute("permissao", 1);
	// 	return "home";
	// }
 

	// @GetMapping("/pet")
	// public String home3(Model model) {
	// 	model.addAttribute("permissao", 2);
	// 	return "home";
	// }

}
