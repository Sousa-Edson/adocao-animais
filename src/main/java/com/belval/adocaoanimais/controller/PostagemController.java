package com.belval.adocaoanimais.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.dto.RequisicaoFormPostagem;
import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
@RequestMapping(value = "/pet/admin/postagem")
public class PostagemController {
	@Autowired
	private PostagemRepository postagemRepository;
	@Autowired
	public static String caminhoImagens = "/home/edson/Imagens/img-data/img-post/";

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("c", new Postagem());
		model.addAttribute("postagens", postagemRepository.findAll());
		return "admin/postagem/index";
	}

	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormPostagem requisicao) {
		ModelAndView mv = new ModelAndView("admin/postagem/new");
		Postagem p = new Postagem();
		if (p.getCaminhoImagem() == null) {
			mv.addObject("p",
					"data:image/jpeg;base64,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");
		} else {
			mv.addObject("p", "/pet/mostrarImagem/img-post/" + p.getCaminhoImagem());
		}
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormPostagem requisicao, BindingResult bindingResult,
			@RequestParam("caminhoImagem") MultipartFile arquivo) {
		System.out.println("#########################################################################CREATE");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			System.out.println("ERRO \n\n" + bindingResult + "\n\n");
			ModelAndView mv = new ModelAndView("admin/postagem/new");
			return mv;
		} else {
			Usuario usuario = new Usuario();
			usuario.setId((long) 1);

			Postagem postagem = requisicao.toPostagem();
			postagem.setUsuario(usuario);
			postagem.setAtivo(true);
			this.postagemRepository.save(postagem);
			System.out.println(
					"###########################################################################\n\n\n ANTES DE CRIAR IMAGEM\n\n\n###############################################");
			createImage(requisicao, arquivo, postagem.getId());
			System.out.println(
					"###########################################################################\n\n\n DEPOIS DE CRIAR IMAGEM\n\n\n###############################################");
			return new ModelAndView("redirect:/pet/admin/postagem/");
		}

	}

	private void createImage(RequisicaoFormPostagem requisicao, MultipartFile arquivo, Long id) {
		System.out.println("######################################\n\n\nID = " + id);
		try {
			Postagem postagem = requisicao.toPostagem();
			System.out.println("entrando");
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens + String.valueOf(id) + "-" + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				postagem.setId(id);
				postagem.setAtivo(true);
				postagem.setCaminhoImagem(String.valueOf(id) + "-" + arquivo.getOriginalFilename());
				this.postagemRepository.save(postagem);
			}
		} catch (Exception e) {
			System.out.println("erro--> " + e);
			e.printStackTrace();
		}
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormPostagem requisicao, Model model) {
		Optional<Postagem> optional = this.postagemRepository.findById(id);

		if (optional.isPresent()) {
			Postagem postagem = optional.get();
			requisicao.fromPostagem(postagem);
			ModelAndView mv = new ModelAndView("admin/postagem/edit");
			mv.addObject("postagemId", postagem.getId());

			Optional<Postagem> p = postagemRepository.findById(id);
			if (p.get() == null) {
				mv.addObject("p",
						"data:image/jpeg;base64,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");
			} else {
				model.addAttribute("p", "/pet/mostrarImagem/img-post/" + p.get().getCaminhoImagem());
			}
			return mv;

		} else {
			System.out.println("$$$$$$$$$$$ Não achou o postagem");
			return new ModelAndView("redirect:/pet/admin/postagem");
		}
	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormPostagem requisicao,
			@RequestParam("caminhoImagem") MultipartFile arquivo, BindingResult bindingResult) {
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/postagem/edit");
			mv.addObject("postagemId", id);
			return mv;
		} else {
			Optional<Postagem> optional = this.postagemRepository.findById(id);
			if (optional.isPresent()) {
				Postagem postagem = requisicao.toPostagem(optional.get());
				postagem.setAtivo(true);
				this.postagemRepository.save(postagem);
				createImage(requisicao, arquivo, postagem.getId());
				return new ModelAndView("redirect:/pet/admin/postagem/");
				// + postagem.getId()
			} else {
				System.out.println("########### Não achou o postagem");
				// return this.retornaError("UPDATE ERROR: raça #" + id + " não encontrado no
				// banco!");
				return null;
			}
		}
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Postagem> p = postagemRepository.findById(id);
		if (p == null) {
			return "nao-encontrada";
		} else {
			model.addAttribute("postagem", p.get());
			return "admin/postagem/show";
		}

	}

	@GetMapping("/{id}/activate")
	public String activate(@PathVariable("id") Long id, Model model, RequisicaoFormPostagem requisicao) {
		Optional<Postagem> e = postagemRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Postagem> optional = this.postagemRepository.findById(id);
			if (optional.isPresent()) {
				Postagem postagem = requisicao.toPostagemCheck(optional.get());
				postagem.setId(id);
				postagem.setAtivo(true);
				this.postagemRepository.save(postagem);
			} else {
				System.out.println("########### Não achou o raça");
			}
			model.addAttribute("racas", postagemRepository.findAll());
		}
		return "redirect:/pet/admin/postagem";
	}

	@GetMapping("/{id}/deactivate")
	public String deactivate(@PathVariable("id") Long id, Model model, RequisicaoFormPostagem requisicao) {
		Optional<Postagem> e = postagemRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Postagem> optional = this.postagemRepository.findById(id);
			if (optional.isPresent()) {
				Postagem postagem = requisicao.toPostagemCheck(optional.get());
				postagem.setId(id);
				postagem.setAtivo(false);
				this.postagemRepository.save(postagem);
			} else {
				System.out.println("########### Não achou o raça");
			}
			model.addAttribute("racas", postagemRepository.findAll());
		}
		return "redirect:/pet/admin/postagem";
	}

	@GetMapping("/{id}/delete")
	public String listExcluir(@PathVariable("id") Long id, Model model) {
		Optional<Postagem> p = postagemRepository.findById(id);
		if (p == null) {
			return "postagem-nao-encontrada";
		} else {
			model.addAttribute("postagens", postagemRepository.findAll());
			model.addAttribute("pId", p);
			model.addAttribute("exc", true);
			return "admin/postagem/index";
		}
	}

	@GetMapping("/{id}/destroy")
	public String excluirConfirmado(@PathVariable("id") Long id, Model model) {
		Optional<Postagem> p = postagemRepository.findById(id);
		if (p == null) {
		} else {
			postagemRepository.deleteById(id);
		}
		return "redirect:/pet/admin/postagem";
	}
}
