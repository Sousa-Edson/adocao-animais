package com.belval.adocaoanimais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.PetRaca;
 
public interface RacaRepository  extends JpaRepository<PetRaca, Long> {
	 
	    // List<PetRaca> findByRaca(String raca);

	    // PetRaca findById(int id);
}
