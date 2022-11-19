package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.belval.adocaoanimais.model.PetRaca;
 
public interface RacaRepository  extends CrudRepository<PetRaca, Integer> {
	 
	    List<PetRaca> findByRaca(String raca);

	    PetRaca findById(int id);
}
