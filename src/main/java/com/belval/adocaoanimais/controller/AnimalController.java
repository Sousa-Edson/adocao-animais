package com.belval.adocaoanimais.controller;



	import java.util.ArrayList;
	import java.util.List;
	import java.util.ListIterator;

	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.servlet.ModelAndView;

	import com.belval.adocaoanimais.model.Animal;

	@Controller
	public class AnimalController {
	

		private static List<Animal> listaPet = new ArrayList<Animal>();
		private static int next= 1;

	private Animal buscarPetPeloId(int id) {
			for(Animal p: listaPet) {
				if(p.getId() == id) {
					return p;
				}
			} return null;
		}

		
		@GetMapping("/pet")
		public String novo(Model model) {
			model.addAttribute("pet", new Animal());
			return "pet-form";
		}

		@PostMapping("/pet")
		public ModelAndView novo(Animal pet) {

			ModelAndView mv = new ModelAndView("redirect:/list");

			if(pet.getId() == 0) {
				pet.setId(next++);
			listaPet.add(pet);
			} else{
				updateAnimal(pet);
			}
			return mv;
			}

			
			

		@GetMapping("/list")
		public String list(Model model) {
			model.addAttribute("pet", listaPet);
			return "pet-list";
		}

		@GetMapping("/pet/{id}")
		public String detalhe(@PathVariable("id") int id, Model model) {
			Animal p= buscarPetPeloId(id);
			if(p== null) {
				return"pet-nao-encontrado";
			}
			model.addAttribute("pet", p);
			return"pet-detalhe";
			}
		@GetMapping("/pet/{id}/edit")
		public String edit(
				@PathVariable("id") int id, Model model) {
			
			Animal p= buscarPetPeloId(id);
			if(p== null) {
				return"pet-nao-encontrado";
			}
			model.addAttribute("pet", p);
			return"pet-form";}
		
		
		
		
		
			
		private void updateAnimal(Animal pet) {
			ListIterator<Animal> i= listaPet.listIterator();
			while(i.hasNext()) {
				Animal atual= i.next(); 
				if(atual.getId() == pet.getId()) {
					i.set(pet);
				}
				}
				}
			

	}