package com.belval.adocaoanimais.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudinary.Cloudinary;

@Controller
public class MostraImagemController {
	@Value("${fileStorageLocation}")
	private static String caminhoImagensPost;

	@Value("${fileStorageLocationAnimal}")
	private static String caminhoImagensAnimal;
	
	@Autowired
	private Cloudinary cloudinary;
	private String cloud="/v1682275689/animais/";

//	@ResponseBody
//	@GetMapping("/pet/mostrarImagem/img-pet/{imagem}")
//	public void retornarImagemPet(@PathVariable("imagem") String imagem, HttpServletResponse response)
//			throws IOException {
//		System.out.println("#########################################################\nAQUI VEM A IMAGEM");
//		// Cria a URL da imagem a partir do Cloudinary
//		String url = cloudinary.url().generate(cloud+imagem);
//				//generate("t" + imagem);
//
//		// Configura o cabeçalho de redirecionamento da resposta
//		response.setHeader("Location", url);
//		response.setStatus(302);
//		System.out.println("#########################################################\nAQUI FINALIZA A IMAGEM");
//	}


	
	MostraImagemController(String caminhoImagensPost, String caminhoImagensAnimal) {
		MostraImagemController.caminhoImagensPost = caminhoImagensPost;
		MostraImagemController.caminhoImagensAnimal = caminhoImagensAnimal;
	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-post/{imagem}")
	public byte[] retornarImagemPostagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagensPost + "/img-post/" + imagem);
		if (imagem == null || imagem.trim().length() <= 3) {
			imagemArquivo = new File("sem-imagem.jpeg");
		}
		return Files.readAllBytes(imagemArquivo.toPath());
	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-post/")
	public byte[] retornarImagemPostagemVazia() throws IOException {
		File imagemArquivo = new File("sem-imagem.jpeg");
		System.out.println(
				"\n\n\n######################################################   Mostra imagem\n\n\nimg-post ---- sem-imagem.jpeg\n:");
		return Files.readAllBytes(imagemArquivo.toPath());
	}

//	@ResponseBody
//	@GetMapping("/pet/mostrarImagem/img-pet/{imagem}")
//	public void retornarImagemPet(@PathVariable("imagem") String imagem, HttpServletResponse response)
//			throws IOException {
//		// Cria a URL da imagem a partir do Cloudinary
//		String url = cloudinary.url().generate("/v1682282021/animais/" + imagem);
//
//		// Configura o cabeçalho de redirecionamento da resposta
//		response.setHeader("Location", url);
//		response.setStatus(302);
//	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-pet/")
	public byte[] retornarImagemPetVazia() throws IOException {
		File imagemArquivo = new File("sem-imagem.jpeg");
		System.out.println(
				"\n\n\n######################################################   Mostra imagem\n\n\nimg-post ---- sem-imagem.jpeg\n:");
		return Files.readAllBytes(imagemArquivo.toPath());
	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-usuario/{imagem}")
	public byte[] retornarImagemUsuario(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagensAnimal + "/img-usuario/" + imagem);
		System.out.println("\n\n\n######################################################   IMAGEM PET\n\n\n");
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
		// @{/pet/mostrarImagem/{imagem}(imagem=${p.getCaminhoImagem})}
	}

	@ResponseBody
	@GetMapping("/pet/mostrarImagem/img-usuario/")
	public byte[] retornarImagemUsuarioVazia() throws IOException {
		File imagemArquivo = new File("sem-imagem.jpeg");
		System.out.println(
				"\n\n\n######################################################   Mostra imagem\n\n\nimg-post ---- sem-imagem.jpeg\n:");
		return Files.readAllBytes(imagemArquivo.toPath());
	}
}
