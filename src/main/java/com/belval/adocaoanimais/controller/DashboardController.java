package com.belval.adocaoanimais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
public class DashboardController {

    @Autowired
	private UsuarioRepository usuarioRepository;

    @GetMapping("/pet/dashboard")
    public String novo() {
        return "dashboard";
    }

    @GetMapping("/pet/dashboard/usuarios")
    public ModelAndView usuarios() {
        ModelAndView mv = new ModelAndView("admin/dashboard/usuarios");
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        mv.addObject("usuario", usuarios);
        mv.addObject("listaPermissao", Permissao.values());
        return mv;
    }
     
}
