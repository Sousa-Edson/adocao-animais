package com.belval.adocaoanimais.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.PetCor;
 
public interface CorRepository extends JpaRepository<PetCor, Long> {

}
