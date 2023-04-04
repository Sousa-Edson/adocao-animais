package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.belval.adocaoanimais.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {


    @Query("select p from Animal p where p.disponivel = true")
    List<Animal> findAllAtivas();
    
//    @Query("select p from Animal p where p.usuario = ?")
//    List<Animal> findMyAnimais();
	 
    List<Animal> findByUsuario_Id(Long usuarioId);
}