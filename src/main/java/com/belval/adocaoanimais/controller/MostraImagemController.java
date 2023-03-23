package com.belval.adocaoanimais.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MostraImagemController {
//	@Autowired
	@Value("${fileStorageLocationPost}")
	private static String caminhoImagensPost;

	@Value("${fileStorageLocationAnimal}")
	private static String caminhoImagensAnimal;

	MostraImagemController(String caminhoImagensPost, String caminhoImagensAnimal) {
		MostraImagemController.caminhoImagensPost = caminhoImagensPost;
		MostraImagemController.caminhoImagensAnimal = caminhoImagensAnimal;
	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-post/{imagem}")
	public byte[] retornarImagemPostagem(@PathVariable("imagem") String imagem) throws IOException {
//		caminhoImagensPost = "eeee";
		File imagemArquivo = new File(caminhoImagensPost + "" + imagem);
		System.out.println("\n\n\n######################################################   ola mundo\n\n\n");
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-pet/{imagem}")
	public byte[] retornarImagemPet(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagensAnimal + "" + imagem);
		System.out.println("\n\n\n######################################################   IMAGEM PET\n\n\n");
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
}
