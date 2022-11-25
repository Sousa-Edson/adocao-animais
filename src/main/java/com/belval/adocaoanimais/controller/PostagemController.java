package com.belval.adocaoanimais.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
public class PostagemController {
	@Autowired
	private PostagemRepository repository;
	@Autowired
	public static String caminhoImagens = "/home/edson/Dev/workspace/adocao-animais/src/main/resources/imagens/img-postagem";
//	public static String caminhoImagens = "D:/Aulas/2022/LIP1/workspace/data";

	@GetMapping("/pet/postagem/postagem-lista")
	public String list(Model model) {

		model.addAttribute("c", new Postagem());
		model.addAttribute("postagem", repository.findAll());
		return "postagem/postagem-lista";
	}

	@GetMapping("/pet/postagem/postagem-novo")
	public String novo() {
		return "postagem/postagem";
	}

	@PostMapping("/pet/postagem/postagem-novo")
	public ModelAndView novo(Postagem postagem, @RequestParam("file-img") MultipartFile arquivo) {
		System.out.println("arquivo " + arquivo);
		ModelAndView mv = new ModelAndView("redirect:../postagem/postagem-lista");
		if (!postagem.getTitulo().isEmpty()) {
			repository.save(postagem);
		}
		try {
			System.out.println("entrando");
			if (!arquivo.isEmpty()) {
				System.out.println("arquivo  = " + arquivo);
				byte[] bytes = arquivo.getBytes();
				System.out.println("byte --> " + bytes.length);
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(postagem.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				postagem.setCaminhoImagem(String.valueOf(postagem.getId()) + arquivo.getOriginalFilename());
				repository.save(postagem);
			}
		} catch (Exception e) {
			System.out.println("erro--> " + e);
			e.printStackTrace();
		}

		return mv;
	}

	@GetMapping("/pet/postagem/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model, ModelMap m) {
		Postagem p = repository.findById(id);
		if (p == null) {
			return "postagem-nao-encontrada";
		} else {

			model.addAttribute("p", p);
//			m.addAttribute("msg", "msg");
			return "postagem/postagem";
		}

	}

	@GetMapping("/pet/postagem/postagem-detalhe/{id}")
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

	@GetMapping("/pet/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			System.out.println("Imagem pasada --> " + imagemArquivo);
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

	@GetMapping("/pet/postagem/postagem-lista/{id}/gerenciar")
	public String listGerenciar(@PathVariable("id") int id, Model model) {
		Postagem p = repository.findById(id);
		if (p == null) {
			return "postagem-nao-encontrada";
		} else {
			model.addAttribute("postagem", repository.findAll());
			model.addAttribute("p", p);
			model.addAttribute("g", true);

			return "postagem/postagem-lista";
		}
	}

	@GetMapping("/pet/postagem/postagem-lista/{id}/excluir")
	public String listExcluir(@PathVariable("id") int id, Model model) {
		Postagem p = repository.findById(id);
		if (p == null) {
			return "postagem-nao-encontrada";
		} else {
			model.addAttribute("postagem", repository.findAll());
			model.addAttribute("p", p);
			model.addAttribute("exc", true);

			return "postagem/postagem-lista";
		}
	}

	@GetMapping("/pet/postagem/postagem-lista/{id}/excluindo")
	public String excluirConfirmado(@PathVariable("id") int id, Model model) {
		Postagem p = repository.findById(id);
		if (p == null) {
			return list(model);
		} else {
			repository.deleteById(id);
			return list(model);
		}
	}
}
