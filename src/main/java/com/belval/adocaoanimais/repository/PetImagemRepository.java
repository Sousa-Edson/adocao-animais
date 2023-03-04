package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetImagem;

 	

public interface PetImagemRepository extends JpaRepository<PetImagem, Long>{
	
	public List<PetImagem> findByAnimal(Animal animal)	;

}
