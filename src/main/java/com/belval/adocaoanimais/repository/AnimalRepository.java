package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.belval.adocaoanimais.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {

	List<Animal> findByNome(String nome);

	Animal findById(int id);

}