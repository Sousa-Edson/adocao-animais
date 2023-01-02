package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.belval.adocaoanimais.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    @Query("select p from Postagem p where p.ativo = true")
    List<Postagem> findAllAtivas();
}
