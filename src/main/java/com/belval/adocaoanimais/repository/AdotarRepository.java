package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.Adotar;

public interface AdotarRepository extends JpaRepository<Adotar, Long> {

 List<Adotar> findByUserId(Long userId);
	 
}