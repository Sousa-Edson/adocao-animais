package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.belval.adocaoanimais.model.PetCor;
 
public interface CorRepository extends CrudRepository<PetCor, Integer> {

    List<PetCor> findByCor(String cor);

    PetCor findById(int id);
}
