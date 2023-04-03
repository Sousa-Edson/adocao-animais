package com.belval.adocaoanimais.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.belval.adocaoanimais.model.Role;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@ControllerAdvice
public class GlobalControllerAdvice {
	@Autowired
	private UsuarioRepository usuarioRepository;


	@ModelAttribute("users")
	public Usuario adicionarUsuarioAoModelo(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			Usuario usuario = new Usuario();
			Usuario user = usuarioRepository.findByEmail(authentication.getName());
			System.out.println(user.getNome());
			usuario=user;
			System.out.println("MINHAS ROLES: "+usuario.getRoles());
			Collection<Role> role = (Collection<Role>) authentication.getAuthorities();
			usuario.setRoles(role);
			System.out.println(role);
			return usuario;
		}
		return null;
	}

}
