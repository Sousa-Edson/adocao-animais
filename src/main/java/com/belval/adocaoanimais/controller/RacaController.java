package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.dto.RequisicaoFormRaca;
import com.belval.adocaoanimais.enums.Especie;
import com.belval.adocaoanimais.model.PetRaca;
import com.belval.adocaoanimais.repository.RacaRepository;

@Controller
@RequestMapping(value = "/pet/admin/pet-raca")
public class RacaController {
	@Autowired
	private RacaRepository racaRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<PetRaca> racas = this.racaRepository.findAll();
		ModelAndView mv = new ModelAndView("raca/index");
		mv.addObject("racas", racas);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormRaca requisicao) {
		ModelAndView mv = new ModelAndView("raca/new");
		mv.addObject("listaEspecie", Especie.values());
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormRaca requisicao, BindingResult bindingResult) {
		System.out.println("#########################################################################CREATE");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			ModelAndView mv = new ModelAndView("raca/new");
			mv.addObject("listaEspecie", Especie.values());
			return mv;
		} else {
			PetRaca petRaca = requisicao.toPetRaca();
			petRaca.setAtivo(true);
			this.racaRepository.save(petRaca);
			return new ModelAndView("redirect:/pet/admin/pet-raca/" + petRaca.getId());
		}
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<PetRaca> optional = this.racaRepository.findById(id);
		if (optional.isPresent()) {
			PetRaca petRaca = optional.get();
			ModelAndView mv = new ModelAndView("raca/show");
			mv.addObject(petRaca);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou a raça");
			return this.retornaError("SHOW ERROR: raça #" + id + " não encontrado no banco!");
		}
	}

	private ModelAndView retornaError(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/pet/admin/pet-raca/new");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormRaca requisicao) {
		Optional<PetRaca> optional = this.racaRepository.findById(id);

		if (optional.isPresent()) {
			PetRaca petRaca = optional.get();
			requisicao.fromPetRaca(petRaca);
			ModelAndView mv = new ModelAndView("raca/edit");
			mv.addObject("petId", petRaca.getId());
			mv.addObject("listaEspecie", Especie.values());
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou o raça");
			return new ModelAndView("redirect:/pet/admin/pet-raca");
		}

	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormRaca requisicao,
			BindingResult bindingResult) {
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("raca/edit");
			mv.addObject("petId", id);
			return mv;
		} else {
			Optional<PetRaca> optional = this.racaRepository.findById(id);
			if (optional.isPresent()) {
				PetRaca petRaca = requisicao.toPetRaca(optional.get());
				petRaca.setAtivo(true);
				this.racaRepository.save(petRaca);
				return new ModelAndView("redirect:/pet/admin/pet-raca/" + petRaca.getId());
			} else {
				System.out.println("########### Não achou o raça");
				return this.retornaError("UPDATE ERROR: raça #" + id + " não encontrado no banco!");
			}
		}
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id, Model model, ModelMap m) {
		Optional<PetRaca> e = racaRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			model.addAttribute("racas", racaRepository.findAll());
			model.addAttribute("racaId", e.get().getId());
			m.addAttribute("exc", true);
		}
		return "admin/raca/index";
	}

	@GetMapping("/{id}/destroy")
	public String destroy(@PathVariable("id") Long id, Model model) {
		try {

			racaRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("#############################################################\n\n\n ERRO " + e
					+ "\n\n\n#############################");
		}
		return "redirect:/pet/admin/pet-raca";
	}

	@GetMapping("/{id}/activate")
	public String activate(@PathVariable("id") Long id, Model model, RequisicaoFormRaca requisicao) {
		Optional<PetRaca> e = racaRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<PetRaca> optional = this.racaRepository.findById(id);
			if (optional.isPresent()) {
				PetRaca petRaca = requisicao.toPetRacaCheck(optional.get());
				petRaca.setId(id);
				petRaca.setAtivo(true);
				this.racaRepository.save(petRaca);
			} else {
				System.out.println("########### Não achou o raça");
			}
			model.addAttribute("racas", racaRepository.findAll());
		}
		return "redirect:/pet/admin/pet-raca";
	}

	@GetMapping("/{id}/deactivate")
	public String deactivate(@PathVariable("id") Long id, Model model, RequisicaoFormRaca requisicao) {
		Optional<PetRaca> e = racaRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<PetRaca> optional = this.racaRepository.findById(id);
			if (optional.isPresent()) {
				PetRaca petRaca = requisicao.toPetRacaCheck(optional.get());
				petRaca.setId(id);
				petRaca.setAtivo(false);
				this.racaRepository.save(petRaca);
			} else {
				System.out.println("########### Não achou o raça");
			}
			model.addAttribute("racas", racaRepository.findAll());
		}
		return "redirect:/pet/admin/pet-raca";
	}
}
