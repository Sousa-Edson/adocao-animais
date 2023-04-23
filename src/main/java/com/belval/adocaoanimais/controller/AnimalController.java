package com.belval.adocaoanimais.controller;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.auxiliar.Menu;
import com.belval.adocaoanimais.dto.RequisicaoFormAnimal;
import com.belval.adocaoanimais.enums.Especie;
import com.belval.adocaoanimais.enums.Porte;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.PetImagemRepository;
import com.belval.adocaoanimais.repository.RacaRepository;
import com.belval.adocaoanimais.repository.UsuarioRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Controller
@RequestMapping(value = "/pet/private/animal")
@PreAuthorize("hasAnyAuthority('ADMIN','COLLABORATOR','SUPPORT','USER')")
public class AnimalController {

	@Value("${fileStorageLocation}")
	public static String caminhoImagens;

	private final Cloudinary cloudinary;

	AnimalController(String caminhoImagens, Cloudinary cloudinary) {
		AnimalController.caminhoImagens = caminhoImagens;
		this.cloudinary = cloudinary;
	}

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private RacaRepository racaRepository;
	@Autowired
	private CorRepository corRepository;

	@Autowired
	private PetImagemRepository petImagemRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	Menu menu = new Menu();
//	private final Cloudinary cloudinary;

//	public CloudinaryService(Cloudinary cloudinary) {
//		this.cloudinary = cloudinary;
//	}

	private String cloud = "https://res.cloudinary.com/duatdkkb3/image/upload/v1682275689/";

	@GetMapping("")
	public ModelAndView index(Authentication authentication) {
		Usuario user = null;
		try {
			user = usuarioRepository.findByEmail(authentication.getName());
		} catch (Exception e) {
		}
		menu.setTitulo("Meus anúncios");
		menu.setSelecao("anuncio");
//		List<Animal> animais = this.animalRepository.findByUserId(user.getId());
		List<Animal> animais = this.animalRepository.findByUsuario_Id(user.getId());
		ModelAndView mv = new ModelAndView("animal/index");
		mv.addObject("animais", animais);
		mv.addObject("menu", menu);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormAnimal requisicao) {
		ModelAndView mv = new ModelAndView("animal/new");
		List<PetRaca> racas = racaRepository.findAll();
		mv.addObject("listaRaca", racas);
		List<PetCor> cores = corRepository.findAll();
		mv.addObject("listaCor", cores);
		mv.addObject("listaEspecie", Especie.values());
		mv.addObject("listaPorte", Porte.values());
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormAnimal requisicao, BindingResult bindingResult,
			Authentication authentication) {
		Usuario user = null;
		if (bindingResult.hasErrors()) {
			System.out.println("ERRO \n\n" + bindingResult + "\n\n");
			List<PetRaca> racas = racaRepository.findAll();
			ModelAndView mv = new ModelAndView("animal/new");
			mv.addObject("listaRaca", racas);
			List<PetCor> cores = corRepository.findAll();
			mv.addObject("listaCor", cores);
			mv.addObject("listaEspecie", Especie.values());
			mv.addObject("listaPorte", Porte.values());
			return mv;
		} else {
			try {
				user = usuarioRepository.findByEmail(authentication.getName());
				System.out.println("ID: " + user.getId());

			} catch (Exception e) {
			}
			Animal animal = requisicao.toAnimal();
			animal.setUsuario(user);
			animal.setDisponivel(true);
			this.animalRepository.save(animal);
			// return new ModelAndView("redirect:/pet/home" + animal.getId());
			System.out.println("IR PARA NEW/ID:" + animal.getId());
			return new ModelAndView("redirect:/pet/private/animal/new/" + animal.getId());
		}
	}

	@GetMapping("/new/{id}")
	public ModelAndView nNewImage(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		ModelAndView mv = new ModelAndView("redirect:/pet/private/animal/");
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();
			mv = new ModelAndView("animal/new-image");
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			mv.addObject("cloudinary", cloud);
			mv.addObject(animal);
		} else {
			System.out.println("$$$$$$$$$$$ Não achou a ");
		}
		return mv;
	}

	@GetMapping("/{id}/activate")
	public String activate(@PathVariable("id") Long id, Model model, RequisicaoFormAnimal requisicao) {
		Optional<Animal> e = animalRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Animal> optional = this.animalRepository.findById(id);
			if (optional.isPresent()) {
				Animal animal = requisicao.toAnimalCheck(optional.get());
				animal.setId(id);
				animal.setDisponivel(true);
				this.animalRepository.save(animal);
			} else {
				System.out.println("########### Não achou o animal");
			}
			model.addAttribute("animais", animalRepository.findAll());
		}
		return "redirect:/pet/private/animal";
	}

	@GetMapping("/{id}/deactivate")
	public String deactivate(@PathVariable("id") Long id, Model model, RequisicaoFormAnimal requisicao) {
		Optional<Animal> e = animalRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<Animal> optional = this.animalRepository.findById(id);
			if (optional.isPresent()) {
				Animal animal = requisicao.toAnimalCheck(optional.get());
				animal.setId(id);
				animal.setDisponivel(false);
				this.animalRepository.save(animal);
			} else {
				System.out.println("########### Não achou o animal");
			}
			model.addAttribute("animais", animalRepository.findAll());
		}
		return "redirect:/pet/private/animal";
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormAnimal requisicao) {
		Optional<Animal> optional = this.animalRepository.findById(id);

		if (optional.isPresent()) {
			ModelAndView mv = new ModelAndView("animal/edit");
			Animal animal = optional.get();
			requisicao.fromAnimal(animal);
			mv.addObject(animal);
			mv.addObject("animalId", animal.getId());
			List<PetRaca> racas = racaRepository.findAll();
			mv.addObject("listaRaca", racas);
			List<PetCor> cores = corRepository.findAll();
			mv.addObject("listaCor", cores);
			mv.addObject("listaEspecie", Especie.values());
			mv.addObject("listaPorte", Porte.values());
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			mv.addObject("cloudinary", cloud);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou o animal");
			return new ModelAndView("redirect:/private/animal");
		}

	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormAnimal requisicao,
			BindingResult bindingResult) {
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("animal");
			mv.addObject("petId", id);
			return mv;
		} else {
			Optional<Animal> optional = this.animalRepository.findById(id);
			if (optional.isPresent()) {
				Animal animal = requisicao.toAnimal(optional.get());
				animal.setDisponivel(true);
				this.animalRepository.save(animal);
				return new ModelAndView("redirect:/pet/private/animal/" + animal.getId());
			} else {
				System.out.println("########### Não achou o animal");
				return this.retornaError("UPDATE ERROR: animal #" + id + " não encontrado no banco!");
			}
		}
	}

	private ModelAndView retornaError(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/pet/private/animal");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {

		System.out.println("**** ID: " + id);
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();

			ModelAndView mv = new ModelAndView("animal/show");
			mv.addObject(animal);
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			mv.addObject("cloudinary", cloud);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
			return this.retornaError("SHOW ERROR: animal #" + id + " não encontrado no banco!");
		}
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id, Model model, ModelMap m) {

		menu.setTitulo("Meus anúncios");
		menu.setSelecao("anuncio");

		try {
			Optional<Animal> e = animalRepository.findById(id);
			if (e == null) {
				System.out.println("555555555555555555555555555555555");
			} else {
				model.addAttribute("animais", animalRepository.findAll());
				model.addAttribute("animalId", e.get().getId());
				m.addAttribute("exc", true);
				model.addAttribute("menu", menu);
			}
		} catch (Exception e) {
			System.err.println("\n\n\n#########################\n\nErro do try cath - delete\n\n" + e
					+ "\n\n################################");
		}
		return "animal/index";
	}

	@GetMapping("/{id}/destroy")
	public String destroy(@PathVariable("id") Long id, Model model) {
		try {

			animalRepository.deleteById(id);
		} catch (Exception e) {
			System.err.println("\n\n\n#########################\n\nErro do try cath - destroy\n\n" + e
					+ "\n\n################################");
		}
		return "redirect:/pet/private/animal";
	}

	/* DELETAR IMAGEM */
	@GetMapping("/img/{id}/destroy")
	public String destroyImagem(@PathVariable("id") Long id, Model model) {
		Optional<PetImagem> pet = petImagemRepository.findById(id);
		try {
			Path caminho = Paths.get(caminhoImagens + pet.get().getCaminhoImagem());
			Files.delete(caminho);

		} catch (Exception e) {
			System.err.println("\n\n\n#########################\n\nErro do try cath - destroy\n\n" + e
					+ "\n\n################################");
		}
		try {
			petImagemRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("erro no banco de dados");
		}
		return "redirect:/pet/private/animal/new/" + pet.get().getAnimal().getId();
	}

	/* PENSAR NESTA PARTE PARA INSERIR IMAGEM DO ANIMAL */
	@PostMapping("/new/{id}")
	public ModelAndView saveImage(@PathVariable Long id, @RequestParam("file-img") MultipartFile arquivo) {
		System.out.println("**** ID: " + id);
		System.out.println("arquivo : " + arquivo);
		Optional<Animal> optional = this.animalRepository.findById(id);
		System.out.println("$$$$$$$$$$$ Não achou a ");

		try {
			PetImagem petImagem = new PetImagem();
			if (!arquivo.isEmpty()) {
				String randomName = UUID.randomUUID().toString();
				String folderName = "animais"; // ou qualquer nome de pasta que você queira usar
				String fileName = folderName + "/" + randomName;

				Map<String, Object> options = new HashMap<>();
				options.put("public_id", fileName);
				Map resultado = cloudinary.uploader().upload(arquivo.getBytes(), options);

				System.out.println("###############################\nImagem: " + resultado.get("url"));
				petImagem.setAnimal(optional.get());
				petImagem.setTipoImagem(arquivo.getContentType());
				petImagem.setTamanhoImagem(arquivo.getSize());
				petImagem.setCaminhoImagem((String) resultado.get("public_id"));
				petImagem.setUrlImagem((String) resultado.get("url"));
				this.petImagemRepository.save(petImagem);
				System.out.println("****************************************************\n" + "tamanho: "
						+ arquivo.getSize());
			}
		} catch (Exception e) {
			System.out.println("erro--> " + e);
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/pet/private/animal/new/" + id);
	}

}
