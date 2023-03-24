package com.belval.adocaoanimais;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.belval.adocaoanimais.enums.Especie;
import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.PostagemRepository;
import com.belval.adocaoanimais.repository.RacaRepository;
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
	
	@Autowired
	PostagemRepository postagemRepository;

	@Autowired
	CorRepository corRepository;

	@Autowired
	RacaRepository racaRepository;

	Permissao permissao;
	
	Especie especie;

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
		
		/** SALVAR POSTAGEM **/
//		tring titulo, String conteudo, String caminhoImagem, String dataPublicacao, URL linkEvento,
//		boolean ativo, Usuario usuario
		Optional<Usuario> usuario=usuarioRepository.findById(1l);
		Usuario uu= usuario.get();
//		postagemRepository.save(new Postagem("Adoção 1", "conteudo","dataPublicacao",true,uu));
		postagemRepository.save(new Postagem("titulo","conteudo","caminhoImagem","dataPublicacao",new URL("https://www.local.com"),true,uu));
		Postagem p = new Postagem();
		 
		p.setLinkEvento( new URL("https:;;www.local.com"));

		/** SALVAR COR **/
		corRepository.save(new PetCor("Preto", true));
		corRepository.save(new PetCor("Amarelo", true));
		corRepository.save(new PetCor("Cinza", true));
		corRepository.save(new PetCor("Marrom", true));
		
		/** SALVAR RACA **/
		racaRepository.save(new PetRaca("Huski", true,especie.CACHORRO));
		racaRepository.save(new PetRaca("Vira-lata", true,especie.CACHORRO));
		racaRepository.save(new PetRaca("Doberman", true,especie.CACHORRO));
		racaRepository.save(new PetRaca("Pit-bull", true,especie.CACHORRO));
		racaRepository.save(new PetRaca("Persa", true,especie.GATO));
		racaRepository.save(new PetRaca("Egípcio", true,especie.GATO));
	}

}