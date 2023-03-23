package com.belval.adocaoanimais.config;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

	@PostConstruct
	public void init() {
		postagem();
		animal();
		usuario();
	}

	@Bean
	public String fileStorageLocationPost() {
		return System.getProperty("user.dir") + "/img-post/";
	}
	 
	public String fileStorageLocationAnimal() {
		return System.getProperty("user.dir") + "/img-animal/";
	}
	 
	public String fileStorageLocationUsuario() {
		return System.getProperty("user.dir") + "/img-usuario/";
	}

	public void postagem() {
		String folderName = "img-post";
		String folderPath = System.getProperty("user.dir") + "/" + folderName;
		File folder = new File(folderPath);

		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	public void animal() {
		String folderName = "img-animal";
		String folderPath = System.getProperty("user.dir") + "/" + folderName;
		File folder = new File(folderPath);

		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	public void usuario() {
		String folderName = "img-usuario";
		String folderPath = System.getProperty("user.dir") + "/" + folderName;
		File folder = new File(folderPath);

		if (!folder.exists()) {
			folder.mkdir();
		}

	}

}
