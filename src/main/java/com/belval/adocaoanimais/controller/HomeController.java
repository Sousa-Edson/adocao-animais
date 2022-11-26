package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
public class HomeController {
	@Autowired
	private PostagemRepository repository;

	@GetMapping("/pet/home")
	public String list(Model model) {
		model.addAttribute("id", 3);
		model.addAttribute("p", repository.findAll());
		return "home";
	}

	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}
 

	@GetMapping("/pet")
	public String home3(Model model) {
		model.addAttribute("id", 1);
		return "home";
	}

}
