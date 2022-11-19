package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.belval.adocaoanimais.model.Postagem;
 
public interface PostagemRepository extends CrudRepository<Postagem, Integer> {

    List<Postagem> findByTitulo(String cor);

    Postagem findById(int id);
}
