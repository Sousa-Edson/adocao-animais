package com.belval.adocaoanimais;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {
	LocalDateTime agora = LocalDateTime.now();
	String s = "25/07/1981";
	DateTimeFormatter parser = new DateTimeFormatterBuilder()
			// dia/mês/ano
			.appendPattern("dd/MM/uuuu")
			// valor default para o horário (zero para meia-noite)
			.parseDefaulting(ChronoField.HOUR_OF_DAY, 9).toFormatter();
	LocalDateTime ontem = LocalDateTime.parse(s, parser);

	@Autowired
	UsuarioRepository usuarioRepository;

	Permissao permissao;

	@Override
	public void run(String... args) throws Exception {

		/** SALVAR USUARIO **/

		usuarioRepository.save(new Usuario("Edson", "Sousa", "675.749.088-93", "10/01/2000", "masculino",
				"edson@edson.com", "11 9999-3333", "123", permissao.SUPORTE, true));

		usuarioRepository.save(new Usuario("Felipe", "Fiere", "480.383.958-16", "01/10/2000", "masculino",
				"felipe@felipe.com", "11 9211-0105", "123", permissao.COLABORADOR, true));

		usuarioRepository.save(new Usuario("Victor", "Bombastic", "230.339.982-06", "01/10/2010", "masculino",
				"victor@victor.com", "11 8521-0105", "123", permissao.USUARIO, true));
		
		usuarioRepository.save(new Usuario("Mariah", "Victoria", "250.789.452-26", "01/10/2010", "feminino",
				"mariah@mariah.com", "11 8581-0158", "123", permissao.ADMINISTRADOR, true));
	}

}