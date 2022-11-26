package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;

	@PostMapping("/pet/usuario/usuario-novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:../login");
		if (!usuario.getNome().isEmpty()) {
			repository.save(usuario);
		}

		return mv;
	}

	@GetMapping("/pet/usuario/usuario-novo")
	public ModelAndView novo2(Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:../login");
		if (!usuario.getNome().isEmpty()) {
			repository.save(usuario);
		}

		return mv;
	}

	/* LOGIN */
	@GetMapping("/pet/login")
	public String login() {
		return "login";
	}

	/* LOGIN-CADASTRO */
	@GetMapping("/pet/register")
	public String cadastro() {
		return "/pessoa/cadastroUsu";
	}
}
