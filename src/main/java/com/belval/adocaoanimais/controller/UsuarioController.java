package com.belval.adocaoanimais.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.dto.RequisicaoFormUsuario;
import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${fileStorageLocationUsuario}")
	public static String caminhoImagens;

	UsuarioController(String caminhoImagens) {
		UsuarioController.caminhoImagens = caminhoImagens;
	}

	@GetMapping("/pet/usuario")
	public ModelAndView nnew( RequisicaoFormUsuario requisicao) {
		return new ModelAndView("newUsuario");
	}

	@GetMapping("/pet/usuario/new2")
	public String nnew2() {
		return "newUsuario2";
	}

	@GetMapping("/pet/login")
	public String login() {
		return "login";
	}

	@PostMapping("/pet/usuario")
	public ModelAndView create(@Valid RequisicaoFormUsuario requisicao, BindingResult bindingResult) {
		System.out.println("\n\n\n###############################################################\n\n"
				+ requisicao.toString() + "\n\n\n###############################################################\n\n");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			ModelAndView mv = new ModelAndView("newUsuario");
			return mv;
		} else {
			Usuario usuario = requisicao.toUsuario();
			usuario.setAtivo(true);
			usuario.setPermissao(Permissao.USUARIO);
			this.usuarioRepository.save(usuario);
			System.out.println("\n\n\n###############################################################\n\n"
					+ usuario.toString() + "\n\n\n###############################################################\n\n");
			return new ModelAndView("redirect:/pet/login");
		}
	}

	/* UPDATE DE PERFIL DE USUARIO */

	@PostMapping("/pet/private/perfil/{id}/perfil")
	public ModelAndView updatePerfil(@PathVariable Long id, @Valid RequisicaoFormUsuario requisicao,
			BindingResult bindingResult) {
		Optional<Usuario> optional = this.usuarioRepository.findById(id);
//		Usuario usuario = requisicao.toUsuario();
		if (bindingResult.hasErrors()) {

			System.out.println("\n************************TEM ERROS (updatePerfil)**********************\n");
			ModelAndView mv = new ModelAndView("usuario/perfil");
			Usuario usuario = optional.get();
			usuario.setId(id);
			mv.addObject("usuario", usuario);
			mv.addObject("idUser", id);
			System.out.println("ERRO: " + bindingResult);
			mv.addObject("erro", true);
			return mv;
		} else {
			System.out.println("\n************************DEU CERTO (updatePerfil)**********************\n");
			Usuario usuario = requisicao.toUsuario();
//			usuario.setAtivo(true);
//			usuario.setPermissao(Permissao.USUARIO);
			this.usuarioRepository.save(usuario);
			return new ModelAndView("redirect:/pet/private/perfil/{id}");
		}
	}

	@GetMapping("/pet/private/perfil/{id}")
	public ModelAndView showUsuario(@PathVariable Long id,RequisicaoFormUsuario requisicao) {
		Optional<Usuario> optional = this.usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = optional.get();
			ModelAndView mv = new ModelAndView("usuario/perfil");
			// mv.addObject("listaPermissao", Permissao.values());
			requisicao.fromUsuario(usuario);
			mv.addObject("idUser", id);
			mv.addObject("usuario", usuario);
			mv.addObject("requisicaoFormUsuario",requisicao);
			return mv;
		} else {
			return new ModelAndView("home");
		}
	}
	
	/* PENSAR NESTA PARTE PARA INSERIR IMAGEM DO ANIMAL */
	@PostMapping("/pet/private/perfil/{id}/imagem")
	public ModelAndView saveImage(@PathVariable Long id, @RequestParam("file-img") MultipartFile arquivo) {
		System.out.println("**** ID: " + id);
		System.out.println("arquivo : " + arquivo);
		Optional<Usuario> optional = this.usuarioRepository.findById(id);
		try {
			Usuario usuario = optional.get();
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens + "/img-usuario/"+ String.valueOf(id) + "-" + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				usuario.setCaminhoImagem(String.valueOf(id) + "-" + arquivo.getOriginalFilename());
				this.usuarioRepository.save(usuario);
			}
		} catch (Exception e) {
			System.out.println("erro--> " + e);
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/pet/private/animal/new/" + id);
	}
	

	/* SEPARAÇÃO DE CADASTRO COMUN PARA A PARTE ADMINISTRATIVA */

	@GetMapping("/pet/admin/usuario")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("usuario/index");
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		mv.addObject("usuario", usuarios);
		// mv.addObject("listaPermissao", Permissao.values());
		return mv;
	}

	@GetMapping("/pet/admin/usuario/permissao/{id}")
	public ModelAndView editPermission(@PathVariable Long id, RequisicaoFormUsuario requisicao) {
		Optional<Usuario> optional = this.usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = optional.get();
			ModelAndView mv = new ModelAndView("usuario/permission");
			requisicao.fromPermissao(usuario);
			mv.addObject("listaPermissao", Permissao.values());
			mv.addObject(usuario);
			return mv;
		} else {
			return new ModelAndView("dashboard");
		}
	}

	@PostMapping("/pet/admin/usuario/permissao/{id}/update")
	public ModelAndView updatePermission(@PathVariable Long id, RequisicaoFormUsuario requisicao) {
		Optional<Usuario> optional = this.usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = requisicao.toPermissao(optional.get());
			usuario.setAtivo(true);
			this.usuarioRepository.save(usuario);
			return new ModelAndView("redirect:/pet/admin/usuario");
			// return new ModelAndView("redirect:/pet/admin/pet-raca/" + petRaca.getId());
		} else {
			System.out.println("########### Não achou o @@@@@@@@@@@@@@@@");
			return new ModelAndView("dashboard");
		}
	}

	@GetMapping("/pet/admin/usuario/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Usuario> optional = this.usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = optional.get();
			ModelAndView mv = new ModelAndView("usuario/show");
			// mv.addObject("listaPermissao", Permissao.values());
			mv.addObject("idUser", id);
			mv.addObject(usuario);
			return mv;
		} else {
			return new ModelAndView("dashboard");
		}
	}

	@GetMapping("/pet/admin/usuario/{id}/activate")
	public String activate(@PathVariable("id") Long id, Model model, RequisicaoFormUsuario requisicao) {
		Optional<Usuario> e = usuarioRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Usuario> optional = this.usuarioRepository.findById(id);
			if (optional.isPresent()) {
				Usuario usuario = requisicao.toUsuarioCheck(optional.get());
				usuario.setId(id);
				usuario.setAtivo(true);
				this.usuarioRepository.save(usuario);
			} else {
				System.out.println("########### Não achou o raça");
			}
			// model.addAttribute("racas", usuarioRepository.findAll());
		}
		return "redirect:/pet/admin/usuario";
	}

	@GetMapping("/pet/admin/usuario/{id}/deactivate")
	public String deactivate(@PathVariable("id") Long id, Model model, RequisicaoFormUsuario requisicao) {
		Optional<Usuario> e = usuarioRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Usuario> optional = this.usuarioRepository.findById(id);
			if (optional.isPresent()) {
				Usuario usuario = requisicao.toUsuarioCheck(optional.get());
				usuario.setId(id);
				usuario.setAtivo(false);
				this.usuarioRepository.save(usuario);
			} else {
				System.out.println("########### Não achou o raça");
			}
			// model.addAttribute("racas", usuarioRepository.findAll());
		}
		return "redirect:/pet/admin/usuario";
	}

	@GetMapping("/pet/admin/usuario/{id}/delete")
	public String delete(@PathVariable("id") Long id, Model model, ModelMap m) {
		Optional<Usuario> e = usuarioRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			model.addAttribute("usuario", usuarioRepository.findAll());
			model.addAttribute("usuarioId", e.get().getId());
			m.addAttribute("exc", true);
		}
		return "usuario/index";
	}

	@GetMapping("/pet/admin/usuario/{id}/destroy")
	public String destroy(@PathVariable("id") Long id, Model model) {
		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("#############################################################\n\n\n ERRO " + e
					+ "\n\n\n#############################");
		}
		return "redirect:/pet/admin/usuario";
	}

}
