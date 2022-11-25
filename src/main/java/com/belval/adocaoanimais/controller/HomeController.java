package com.belval.adocaoanimais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return index();
	}

	@GetMapping("/home")
	public String home2() {
		return index();
	}

	@GetMapping("/pet")
	public String home3() {
		return index();
	}

	@GetMapping("/pet/home")
	public String index() {
		return "home";
	}

}
