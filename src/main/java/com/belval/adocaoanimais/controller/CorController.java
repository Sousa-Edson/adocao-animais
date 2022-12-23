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
		ModelAndView mv = new ModelAndView("admin/cor/index");
		mv.addObject("cores", cores);
		return mv;
	}

	@GetMapping("/new")
	public ModelAndView nnew(RequisicaoFormCor requisicao) {
		ModelAndView mv = new ModelAndView("admin/cor/new");

		return mv;
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormCor requisicao, BindingResult bindingResult) {
		System.out.println("#########################################################################CREATE");
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			ModelAndView mv = new ModelAndView("admin/cor/new");

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
			ModelAndView mv = new ModelAndView("admin/cor/show");
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
			ModelAndView mv = new ModelAndView("admin/cor/edit");
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
			ModelAndView mv = new ModelAndView("admin/cor/edit");
			mv.addObject("petId", id);
			return mv;
		} else {
			Optional<PetCor> optional = this.corRepository.findById(id);
			if (optional.isPresent()) {
				PetCor petCor = requisicao.toPetCor(optional.get());
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
	public String excluir(@PathVariable("id") Long id, Model model, ModelMap m) {
		// repository.deleteById(id);
		Optional<PetCor> e = corRepository.findById(id);
		// System.out.println("ver -------------- "+e.get());
		if (e == null) {
			System.out.println("555555555555555555555555555555555");
			return "redirect:/pet/admin/pet-cor";
		} else {
			System.out.println("99999999999999999999999999999999999999");
			System.out.println(e.get().getId());
			model.addAttribute("cores", corRepository.findAll());
			model.addAttribute("corId", e.get().getId());
			m.addAttribute("exc", true);
			System.out.println(m);
			return "admin/cor/index";
		}
	}

	@GetMapping("/{id}/destroy")
	public String excluir(@PathVariable("id") Long id, Model model) {
		corRepository.deleteById(id);

		return "redirect:/pet/admin/pet-cor";

	}

	// public ModelAndView delete(@PathVariable Long id ) {
	// Optional<PetCor> optional = this.corRepository.findById(id);
	// if (optional == null) {
	// return index();
	// } else {
	// System.out.println(optional.get());
	// ModelAndView mv = new ModelAndView("redirect:/");
	// mv.addObject("cor", corRepository.findAll());
	// mv.addObject("exc", true);
	// return mv;
	// }
}

// @PostMapping("/pet/pet-cor")
// public ModelAndView novo(PetCor cor) {
// ModelAndView mv = new ModelAndView("redirect:../pet/pet-cor");
// if (!cor.getCor().isEmpty()) {
// repository.save(cor);
// }
//
// return mv;
// }
//
// @GetMapping("/pet/pet-cor")
// public String list(Model model) {
// model.addAttribute("c", new PetCor());
// model.addAttribute("cor", repository.findAll());
// return "animal/pet-cor";
// }
//
// @GetMapping("/pet/pet-cor/{id}/edit")
// public String edit(@PathVariable("id") int id, Model model, ModelMap m) {
// PetCor c = repository.findById(id);
// if (c == null) {
// return "cor-nao-encontrada";
// } else {
// model.addAttribute("cor", repository.findAll());
// model.addAttribute("c", c);
// m.addAttribute("msg", "msg");
// return "animal/pet-cor";
// }
//
// }

// @GetMapping("/pet/pet-cor/{id}/excluir")
// public String excluir(@PathVariable("id") int id, Model model) {
// repository.deleteById(id);
//
// return list(model);
//
// }

// @GetMapping("/pet/pet-cor/{id}/excluir")
// public String excluir(@PathVariable("id") int id, Model model, ModelMap m) {
// // repository.deleteById(id);
// PetCor e = repository.findById(id);
// if (e == null) {
// return list(model);
// } else {
// System.out.println(e.getId());
// model.addAttribute("cor", repository.findAll());
// model.addAttribute("c", new PetCor());
// model.addAttribute("e", e);
// m.addAttribute("exc", true);
// return "animal/pet-cor";
// }
// }
//
// @GetMapping("/pet/pet-cor/{id}/excluindo")
// public String excluirConfirmado(@PathVariable("id") int id, Model model) {
// PetCor e = repository.findById(id);
// if (e == null) {
// return list(model);
// } else {
// repository.deleteById(id);
// return list(model);
// }
// }
