package com.belval.adocaoanimais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AdocaoanimaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdocaoanimaisApplication.class, args);
	}
	@GetMapping("/")
	public String index() {
		return "coloque na url  /pet/home";
	}

}