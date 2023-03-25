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
import com.belval.adocaoanimais.enums.Porte;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.AnimalRepository;
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

	@Autowired
	AnimalRepository animalRepository;

	Permissao permissao;

//	Especie especie;

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
		Optional<Usuario> usuario = usuarioRepository.findById(1l);
		Usuario user = usuario.get();

		postagemRepository.save(
				new Postagem("titulo", "conteudo", "", "dataPublicacao", new URL("https://www.local.com"), true, user));
		postagemRepository.save(new Postagem("Na quarta-feira acontece o agendamento de castração de pets",
				"Será aberto na quarta-feira (8 de março) o agendamento para castração de cães e gatos, promovido pelo Centro de Proteção ao Animal Doméstico (Cepad), da Secretaria de Recursos Naturais e Meio Ambiente de Barueri (Sema).\r\n"
						+ "\r\n"
						+ "Ele ocorrerá exclusivamente pela internet, clicando AQUI. As vagas são limitadas, totalizando 230 (80 caninos fêmeas, 50 caninos machos, 60 felinos fêmeas e 40 felinos machos). O agendamento permanecerá aberto até que as vagas acabem. \r\n"
						+ "\r\n"
						+ "O tutor ficará sabendo sobre o dia, horário, local e procedimentos para cirurgia no momento da marcação. É importante ler atentamente as as orientações pré-operatórias expostas na Ficha de Agendamento, que deverá ser impressa, preenchida e entregue no dia da cirurgia. \r\n"
						+ "",
				"quarta.jpg", " 01 DE MARÇO DE 2023",
				new URL("https://portal.barueri.sp.gov.br/Noticia/01032023-na-quarta-feira-acontece-o-agendamento-de-castracao-de-pets"),
				true, user));

		postagemRepository.save(new Postagem("Feira Especial de Adoção de Pets acontece neste sábado",
				" No próximo sábado, dia 11, acontece a Feira Especial de Adoção de Pets do Cepad (Centro de Proteção ao Animal Doméstico), da Secretaria de Recursos Naturais e Meio Ambiente (Sema). O evento, aberto ao público, será das 9h às 16h na unidade 1 do Cepad, que fica na rua Vera Cruz, 340 - Bairro dos Altos.\r\n"
						+ "\r\n"
						+ "O Cepad disponibiliza para adoção os cães e gatos resgatados pelo setor. Os animais são tratados completamente e passam por uma adaptação até serem colocados para adoção. Cada animal adotado abre espaço para que o Cepad resgate mais pets em situação de abandono ou em risco de morte.",
				"feira.jpg", " 09 DE FEVEREIRO DE 2023",
				new URL("https://portal.barueri.sp.gov.br/Noticia/08022023-feira-especial-de-adocao-de-pets-acontece-neste-sabado"),
				true, user));

		/** SALVAR COR **/
		corRepository.save(new PetCor("Preto", true));
		corRepository.save(new PetCor("Amarelo", true));
		corRepository.save(new PetCor("Cinza", true));
		corRepository.save(new PetCor("Marrom", true));

		/** SALVAR RACA **/
		racaRepository.save(new PetRaca("Huski", true, Especie.CACHORRO));
		racaRepository.save(new PetRaca("Vira-lata", true, Especie.CACHORRO));
		racaRepository.save(new PetRaca("Doberman", true, Especie.CACHORRO));
		racaRepository.save(new PetRaca("Pit-bull", true, Especie.CACHORRO));
		racaRepository.save(new PetRaca("Persa", true, Especie.GATO));
		racaRepository.save(new PetRaca("Egípcio", true, Especie.GATO));

		/** SALVAR ANIMAL **/

		Animal a = new Animal();
		a.setNome("Trovão");
		a.setObservacao(
				"	Lorem ipsum arcu aptent integer suspendisse urna nec, platea porttitor aliquam class mauris feugiat tempor, sollicitudin a integer porttitor aenean curabitur. conubia lobortis curae donec libero primis maecenas, ut conubia ligula proin augue metus, lacus varius accumsan phasellus interdum. dui rutrum id curabitur donec imperdiet himenaeos conubia nisl, arcu maecenas pharetra convallis semper rutrum lobortis. ");
		a.setResumo(
				"	Purus commodo dapibus urna suscipit magna morbi eget senectus, lacus pharetra placerat aliquam ornare sapien curabitur leo, ullamcorper risus a consectetur sociosqu pretium nibh. ligula blandit fusce quisque libero lobortis lacinia ut turpis ipsum eget, odio imperdiet consequat augue semper sociosqu interdum luctus interdum neque primis, neque porttitor nec odio imperdiet nunc convallis et rutrum. ");
		a.setDisponivel(true);
		a.setPetCor(corRepository.findById(1l).get());
		a.setPetRaca(racaRepository.findById(1l).get());
		a.setPorte(Porte.GRANDE);
		a.setSexo(1);
		a.setEspecie(Especie.CACHORRO);
		animalRepository.save(a);
	}

}