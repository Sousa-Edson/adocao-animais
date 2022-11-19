package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
public class PostagemController {
	@Autowired
	private PostagemRepository repository;

	 

	@GetMapping("/postagem/postagem-lista")
	public String list(Model model) {

		model.addAttribute("c", new Postagem());
		model.addAttribute("postagem", repository.findAll());
		return "postagem/postagem-lista";
	}
	

	@GetMapping("/postagem/postagem-novo")
	public String novo() {
		return "postagem/postagem";
	}

	@PostMapping("/postagem/postagem-novo")
	public ModelAndView novo(Postagem postagem) {

		ModelAndView mv = new ModelAndView("redirect:../postagem/postagem-lista");

		if (!postagem.getTitulo().isEmpty()) {
			repository.save(postagem);
		}

		return mv;
	}

 

//	@GetMapping("/postagem/postagem-detalhe")
//	public String detalhe() {
//		return "postagem/postagem-detalhe";
//	}

	@GetMapping("/postagem/postagem-detalhe/{id}")
	public String detalhe(@PathVariable("id") int id, Model model ) {
		System.out.println("ola");
		Postagem c = repository.findById(id);
		if (c == null) {
			return "nao-encontrada";
		} else {
			model.addAttribute("postagem", repository.findAll());
			model.addAttribute("c", c);
			 System.out.println(c.getTitulo());
			return "postagem/postagem-detalhe";
		}

	}
	
	
//	@PostMapping("/postagem/postagem-detalhe")
//	public String detalhe2() {
//		return "postagem/postagem-detalhe";
//	}
}
