package com.belval.adocaoanimais.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
public class PostagemController {
	@Autowired
	private PostagemRepository repository;
//	@Autowired
//	public static String caminhoImagens = "/home/edson/Dev/workspace/adocao-animais/src/main/resources/imagens/";
//	public static String caminhoImagens = "D:/Aulas/2022/LIP1/workspace/data";

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
	public ModelAndView novo(Postagem postagem, @RequestParam("file") MultipartFile arquivo) {
		System.out.println("arquivo " + arquivo);
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
	public String detalhe(@PathVariable("id") int id, Model model) {
		System.out.println("ola");
		Postagem c = repository.findById(id);
		System.out.println("titulo -- " + c.getTitulo());
		if (c == null) {
			return "nao-encontrada";
		} else {
			System.out.println("pega" + c.getCaminhoImagem());
			model.addAttribute("postagem", repository.findAll());
			model.addAttribute("c", c);
			return "postagem/postagem-detalhe";
		}

	}

//	@PostMapping("/postagem/postagem-detalhe")
//	public String detalhe2() {
//		return "postagem/postagem-detalhe";
//	}

	/*
	 * AQUI TESTE PARA SALVAR IMAGEM EM LOCAL -> MAS SEM SUCESSO SALVO EM PASTA MAS
	 * NAÃO CONSIGO RETORNAR OU RETORNO DE RESOUCES MAS NAO CONSIGO SALVAR LA
	 * 
	 * @PostMapping("/postagem/postagem-novo") public ModelAndView novo(Postagem
	 * postagem, @RequestParam("file") MultipartFile arquivo) {
	 * System.out.println("arquivo " + arquivo); ModelAndView mv = new
	 * ModelAndView("redirect:../postagem/postagem-lista"); if
	 * (!postagem.getTitulo().isEmpty()) { repository.save(postagem); } try {
	 * System.out.println("entrando"); if (!arquivo.isEmpty()) {
	 * System.out.println("arquivo  = " + arquivo); byte[] bytes =
	 * arquivo.getBytes(); Path caminho = Paths .get(caminhoImagens +
	 * String.valueOf(postagem.getId()) + arquivo.getOriginalFilename());
	 * Files.write(caminho, bytes);
	 * postagem.setCaminhoImagem(String.valueOf(postagem.getId()) +
	 * arquivo.getOriginalFilename());
	 * System.out.println("img -- "+postagem.getCaminhoImagem());
	 * repository.save(postagem); } } catch (Exception e) {
	 * System.out.println("erro"); System.out.println(e); e.printStackTrace(); }
	 * 
	 * return mv; }
	 * 
	 * @GetMapping("/postagem/postagem-detalhe/{id}") public String
	 * detalhe(@PathVariable("id") int id, Model model) { System.out.println("ola");
	 * Postagem c = repository.findById(id);
	 * System.out.println("titulo -- "+c.getTitulo()); if (c == null) { return
	 * "nao-encontrada"; } else { System.out.println("pega"+c.getCaminhoImagem());
	 * model.addAttribute("postagem", repository.findAll()); model.addAttribute("c",
	 * c); model.addAttribute("i", caminhoImagens); return
	 * "postagem/postagem-detalhe"; }
	 * 
	 * }
	 */
}
