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

import com.belval.adocaoanimais.dto.RequisicaoFormCor;
import com.belval.adocaoanimais.model.PetCor;
import com.belval.adocaoanimais.repository.CorRepository;

@Controller
@RequestMapping(value = "/pet/admin/pet-cor")
public class CorController {
	@Autowired
	private CorRepository corRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<PetCor> cores = this.corRepository.findAll();
		ModelAndView mv = new ModelAndView("cor/index");
		mv.addObject("cores", cores);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormCor requisicao) {
		ModelAndView mv = new ModelAndView("cor/new");
		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormCor requisicao, BindingResult bindingResult) {
		System.out.println("#########################################################################CREATE");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			ModelAndView mv = new ModelAndView("cor/new");

			return mv;
		} else {
			PetCor petCor = requisicao.toPetCor();
			petCor.setAtivo(true);
			this.corRepository.save(petCor);
			return new ModelAndView("redirect:/pet/admin/pet-cor/" + petCor.getId());
		}
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<PetCor> optional = this.corRepository.findById(id);
		if (optional.isPresent()) {
			PetCor petCor = optional.get();
			ModelAndView mv = new ModelAndView("cor/show");
			mv.addObject(petCor);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou a cor");
			return this.retornaError("SHOW ERROR: Cor #" + id + " não encontrado no banco!");
		}
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormCor requisicao) {
		Optional<PetCor> optional = this.corRepository.findById(id);

		if (optional.isPresent()) {
			PetCor petCor = optional.get();
			requisicao.fromPetCor(petCor);
			ModelAndView mv = new ModelAndView("cor/edit");
			mv.addObject("petId", petCor.getId());
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou o cor");
			return new ModelAndView("redirect:/pet/admin/pet-cor");
		}

	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormCor requisicao,
			BindingResult bindingResult) {
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("cor/edit");
			mv.addObject("petId", id);
			return mv;
		} else {
			Optional<PetCor> optional = this.corRepository.findById(id);
			if (optional.isPresent()) {
				PetCor petCor = requisicao.toPetCor(optional.get());
				petCor.setAtivo(true);
				this.corRepository.save(petCor);
				return new ModelAndView("redirect:/pet/admin/pet-cor/" + petCor.getId());
			} else {
				System.out.println("########### Não achou o cor");
				return this.retornaError("UPDATE ERROR: cor #" + id + " não encontrado no banco!");
			}
		}
	}

	private ModelAndView retornaError(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/pet/admin/pet-cor/new");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id, Model model, ModelMap m) {
		Optional<PetCor> e = corRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			model.addAttribute("cores", corRepository.findAll());
			model.addAttribute("corId", e.get().getId());
			m.addAttribute("exc", true);
		}
		return "admin/cor/index";
	}

	@GetMapping("/{id}/destroy")
	public String destroy(@PathVariable("id") Long id, Model model) {
		corRepository.deleteById(id);
		return "redirect:/pet/admin/pet-cor";
	}

	@GetMapping("/{id}/activate")
	public String activate(@PathVariable("id") Long id, Model model, RequisicaoFormCor requisicao) {
		Optional<PetCor> e = corRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<PetCor> optional = this.corRepository.findById(id);
			if (optional.isPresent()) {
				PetCor petCor = requisicao.toPetCorCheck(optional.get());
				petCor.setId(id);
				petCor.setAtivo(true);
				this.corRepository.save(petCor);
			} else {
				System.out.println("########### Não achou o cor");
			}
			model.addAttribute("cores", corRepository.findAll());
		}
		return "redirect:/pet/admin/pet-cor";
	}

	@GetMapping("/{id}/deactivate")
	public String deactivate(@PathVariable("id") Long id, Model model, RequisicaoFormCor requisicao) {
		Optional<PetCor> e = corRepository.findById(id);
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
		} else {
			Optional<PetCor> optional = this.corRepository.findById(id);
			if (optional.isPresent()) {
				PetCor petCor = requisicao.toPetCorCheck(optional.get());
				petCor.setId(id);
				petCor.setAtivo(false);
				this.corRepository.save(petCor);
			} else {
				System.out.println("########### Não achou o cor");
			}
			model.addAttribute("cores", corRepository.findAll());
		}
		return "redirect:/pet/admin/pet-cor";
	}

}
