package com.belval.adocaoanimais;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.belval.adocaoanimais.enums.Especie;
import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.enums.Porte;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.model.Postagem;
import com.belval.adocaoanimais.model.Role;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.PetImagemRepository;
import com.belval.adocaoanimais.repository.PostagemRepository;
import com.belval.adocaoanimais.repository.RacaRepository;
import com.belval.adocaoanimais.repository.RoleRepository;
import com.belval.adocaoanimais.repository.UsuarioRepository;

//@Component
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

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PetImagemRepository petImagemRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	Permissao permissao;

//	Especie especie;

	@Override
	public void run(String... args) throws Exception {
		/** SALVAR ROLE **/
		roleRepository.save(new Role("USER"));
		roleRepository.save(new Role("ADMIN"));
		roleRepository.save(new Role("COLLABORATOR"));
		roleRepository.save(new Role("SUPPORT"));

		Collection<Role> adminRole = roleRepository.findByRole("ADMIN");
		Collection<Role> userRole = roleRepository.findByRole("USER");
		Collection<Role> collaboratorRole = roleRepository.findByRole("COLLABORATOR");
		Collection<Role> supportRole = roleRepository.findByRole("SUPPORT");

		/** SALVAR USUARIO **/
		usuarioRepository
				.save(new Usuario("Edson", "Sousa", "675.749.088-93", "10/01/2000", "masculino", "edson@edson.com",
						"11 9999-3333", passwordEncoder.encode("123"), permissao.SUPORTE, true, adminRole));
		usuarioRepository
				.save(new Usuario("Felipe", "Fiere", "480.383.958-16", "01/10/2000", "masculino", "felipe@felipe.com",
						"11 9211-0105", passwordEncoder.encode("123"), permissao.COLABORADOR, true, collaboratorRole));
		usuarioRepository.save(new Usuario("Victor", "Bombastic", "230.339.982-06", "01/10/2010", "masculino",
				"victor@victor.com", "11 8521-0105", passwordEncoder.encode("123"), permissao.USUARIO, true, userRole));
		usuarioRepository
				.save(new Usuario("Mariah", "Victoria", "250.789.452-26", "01/10/2010", "feminino", "mariah@mariah.com",
						"11 8581-0158", passwordEncoder.encode("123"), permissao.ADMINISTRADOR, true, adminRole));

		/** SALVAR POSTAGEM **/
		Optional<Usuario> usuario = usuarioRepository.findById(1l);
		Usuario user = usuario.get();

		postagemRepository.save(
				new Postagem("titulo", "conteudo", "http://res.cloudinary.com/duatdkkb3/image/upload/v1682299820/postagem/6462dfe0-bc5b-4d74-a284-fcf82e45f47e.jpg", "dataPublicacao", new URL("https://www.local.com"), true, user));
		postagemRepository.save(new Postagem("Na quarta-feira acontece o agendamento de castração de pets",
				"Será aberto na quarta-feira (8 de março) o agendamento para castração de cães e gatos, promovido pelo Centro de Proteção ao Animal Doméstico (Cepad), da Secretaria de Recursos Naturais e Meio Ambiente de Barueri (Sema).\r\n"
						+ "\r\n"
						+ "Ele ocorrerá exclusivamente pela internet, clicando AQUI. As vagas são limitadas, totalizando 230 (80 caninos fêmeas, 50 caninos machos, 60 felinos fêmeas e 40 felinos machos). O agendamento permanecerá aberto até que as vagas acabem. \r\n"
						+ "\r\n"
						+ "O tutor ficará sabendo sobre o dia, horário, local e procedimentos para cirurgia no momento da marcação. É importante ler atentamente as as orientações pré-operatórias expostas na Ficha de Agendamento, que deverá ser impressa, preenchida e entregue no dia da cirurgia. \r\n"
						+ "",
				"http://res.cloudinary.com/duatdkkb3/image/upload/v1682300353/postagem/44038fb3-1d05-42b0-8fc5-f67508359faf.jpg", " 01 DE MARÇO DE 2023",
				new URL("https://portal.barueri.sp.gov.br/Noticia/01032023-na-quarta-feira-acontece-o-agendamento-de-castracao-de-pets"),
				true, user));

		postagemRepository.save(new Postagem("Feira Especial de Adoção de Pets acontece neste sábado",
				" No próximo sábado, dia 11, acontece a Feira Especial de Adoção de Pets do Cepad (Centro de Proteção ao Animal Doméstico), da Secretaria de Recursos Naturais e Meio Ambiente (Sema). O evento, aberto ao público, será das 9h às 16h na unidade 1 do Cepad, que fica na rua Vera Cruz, 340 - Bairro dos Altos.\r\n"
						+ "\r\n"
						+ "O Cepad disponibiliza para adoção os cães e gatos resgatados pelo setor. Os animais são tratados completamente e passam por uma adaptação até serem colocados para adoção. Cada animal adotado abre espaço para que o Cepad resgate mais pets em situação de abandono ou em risco de morte.",
				"http://res.cloudinary.com/duatdkkb3/image/upload/v1682300279/postagem/7d5341a7-1f6a-45a0-afd2-bb8483d13498.jpg", " 09 DE FEVEREIRO DE 2023",
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

		/** DATA **/
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();

		/** SALVAR ANIMAL **/

		Animal a = new Animal();

		a.setNome("Trovão");
		data = formato.parse("18/05/2005");
		a.setNascimento(new java.sql.Date(data.getTime()));
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
		a.setPetImagem(null);
		a.setUsuario(usuarioRepository.findById(1l).get());
		animalRepository.save(a);

		Animal b = new Animal();
		b.setNome("Raio");
		data = formato.parse("16/06/2006");
		b.setNascimento(new java.sql.Date(data.getTime()));
		b.setObservacao(
				"	Lorem ipsum arcu aptent integer suspendisse urna nec, platea porttitor aliquam class mauris feugiat tempor, sollicitudin a integer porttitor aenean curabitur. conubia lobortis curae donec libero primis maecenas, ut conubia ligula proin augue metus, lacus varius accumsan phasellus interdum. dui rutrum id curabitur donec imperdiet himenaeos conubia nisl, arcu maecenas pharetra convallis semper rutrum lobortis. ");
		b.setResumo(
				"	Purus commodo dapibus urna suscipit magna morbi eget senectus, lacus pharetra placerat aliquam ornare sapien curabitur leo, ullamcorper risus a consectetur sociosqu pretium nibh. ligula blandit fusce quisque libero lobortis lacinia ut turpis ipsum eget, odio imperdiet consequat augue semper sociosqu interdum luctus interdum neque primis, neque porttitor nec odio imperdiet nunc convallis et rutrum. ");
		b.setDisponivel(true);
		b.setPetCor(corRepository.findById(1l).get());
		b.setPetRaca(racaRepository.findById(1l).get());
		b.setPorte(Porte.MEDIO);
		b.setSexo(2);
		b.setEspecie(Especie.CACHORRO);
		b.setPetImagem(null);
		b.setUsuario(usuarioRepository.findById(2l).get());
		animalRepository.save(b);

		Animal c = new Animal();
		c.setNome("Relampago");
		data = formato.parse("17/07/2007");
		c.setNascimento(new java.sql.Date(data.getTime()));
		c.setObservacao(
				"	Lorem ipsum arcu aptent integer suspendisse urna nec, platea porttitor aliquam class mauris feugiat tempor, sollicitudin a integer porttitor aenean curabitur. conubia lobortis curae donec libero primis maecenas, ut conubia ligula proin augue metus, lacus varius accumsan phasellus interdum. dui rutrum id curabitur donec imperdiet himenaeos conubia nisl, arcu maecenas pharetra convallis semper rutrum lobortis. ");
		c.setResumo(
				"	Purus commodo dapibus urna suscipit magna morbi eget senectus, lacus pharetra placerat aliquam ornare sapien curabitur leo, ullamcorper risus a consectetur sociosqu pretium nibh. ligula blandit fusce quisque libero lobortis lacinia ut turpis ipsum eget, odio imperdiet consequat augue semper sociosqu interdum luctus interdum neque primis, neque porttitor nec odio imperdiet nunc convallis et rutrum. ");
		c.setDisponivel(true);
		c.setPetCor(corRepository.findById(1l).get());
		c.setPetRaca(racaRepository.findById(1l).get());
		c.setPorte(Porte.PEQUENO);
		c.setSexo(2);
		c.setEspecie(Especie.GATO);
		c.setPetImagem(null);
		c.setUsuario(usuarioRepository.findById(3l).get());
		animalRepository.save(c);

		Animal d = new Animal();
		d.setNome("Nuvem");
		data = formato.parse("19/09/2009");
		d.setNascimento(new java.sql.Date(data.getTime()));
		d.setObservacao(
				"	Lorem ipsum arcu aptent integer suspendisse urna nec, platea porttitor aliquam class mauris feugiat tempor, sollicitudin a integer porttitor aenean curabitur. conubia lobortis curae donec libero primis maecenas, ut conubia ligula proin augue metus, lacus varius accumsan phasellus interdum. dui rutrum id curabitur donec imperdiet himenaeos conubia nisl, arcu maecenas pharetra convallis semper rutrum lobortis. ");
		d.setResumo(
				"	Purus commodo dapibus urna suscipit magna morbi eget senectus, lacus pharetra placerat aliquam ornare sapien curabitur leo, ullamcorper risus a consectetur sociosqu pretium nibh. ligula blandit fusce quisque libero lobortis lacinia ut turpis ipsum eget, odio imperdiet consequat augue semper sociosqu interdum luctus interdum neque primis, neque porttitor nec odio imperdiet nunc convallis et rutrum. ");
		d.setDisponivel(true);
		d.setPetCor(corRepository.findById(1l).get());
		d.setPetRaca(racaRepository.findById(1l).get());
		d.setPorte(Porte.GRANDE);
		d.setSexo(0);
		d.setEspecie(Especie.GATO);
		d.setPetImagem(null);
		d.setUsuario(usuarioRepository.findById(4l).get());
		animalRepository.save(d);

		Animal e = new Animal();
		e.setNome("Chuva");
		data = formato.parse("20/10/2010");
		e.setNascimento(new java.sql.Date(data.getTime()));
		e.setObservacao(
				"	Lorem ipsum arcu aptent integer suspendisse urna nec, platea porttitor aliquam class mauris feugiat tempor, sollicitudin a integer porttitor aenean curabitur. conubia lobortis curae donec libero primis maecenas, ut conubia ligula proin augue metus, lacus varius accumsan phasellus interdum. dui rutrum id curabitur donec imperdiet himenaeos conubia nisl, arcu maecenas pharetra convallis semper rutrum lobortis. ");
		e.setResumo(
				"	Purus commodo dapibus urna suscipit magna morbi eget senectus, lacus pharetra placerat aliquam ornare sapien curabitur leo, ullamcorper risus a consectetur sociosqu pretium nibh. ligula blandit fusce quisque libero lobortis lacinia ut turpis ipsum eget, odio imperdiet consequat augue semper sociosqu interdum luctus interdum neque primis, neque porttitor nec odio imperdiet nunc convallis et rutrum. ");
		e.setDisponivel(true);
		e.setPetCor(corRepository.findById(1l).get());
		e.setPetRaca(racaRepository.findById(1l).get());
		e.setPorte(Porte.MEDIO);
		e.setSexo(1);
		e.setEspecie(Especie.CACHORRO);
		e.setPetImagem(null);
		e.setUsuario(usuarioRepository.findById(1l).get());
		animalRepository.save(e);

		System.out.println("############################## nascimento: " + a.getNascimento());
		
		/** SALVAR IMAGEM **/
		
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301201/animais/91578538-b1e5-46fb-b7ab-978d781db417.jpg",a));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301201/animais/91578538-b1e5-46fb-b7ab-978d781db417.jpg",a));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301255/animais/f3ec9a7a-5d51-49d9-8ea5-a5ed530c162c.jpg",b));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301255/animais/f3ec9a7a-5d51-49d9-8ea5-a5ed530c162c.jpg",b));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301255/animais/f3ec9a7a-5d51-49d9-8ea5-a5ed530c162c.jpg",b));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301291/animais/eb5d5c45-d4c6-4fb6-a491-f552a1a39f48.jpg",c));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301323/animais/608a7e6b-22a2-41eb-89f4-427a7359c150.jpg",d));
		petImagemRepository.save(new PetImagem("http://res.cloudinary.com/duatdkkb3/image/upload/v1682301348/animais/6a8231ee-0e04-41ee-a877-58c62ca0b999.jpg",e));
		
			
	}
	


}