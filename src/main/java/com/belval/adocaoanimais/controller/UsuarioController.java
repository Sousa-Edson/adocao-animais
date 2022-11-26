package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;

	@PostMapping("/pet/usuario/usuario-novo")
	public ModelAndView registro(Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:../login/novo");
		if (!usuario.getNome().isEmpty()) {
			repository.save(usuario);
			mv = new ModelAndView("redirect:../login");
		}

		return mv;
	}

	/* LOGIN */
	@GetMapping("/pet/login")
	public String login() {
		return "login";
	}

	/* LOGIN-CADASTRO */
	@GetMapping("/pet/login/novo")
	public ModelAndView cadastro(Usuario usuario, Model model) {
		ModelAndView mv = new ModelAndView("/pessoa/cadastroUsu");
		 
//			usuario.setNome("Tadeo");
			model.addAttribute("u", usuario);
		 
		return mv;
	}
}
