package com.belval.adocaoanimais.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.belval.adocaoanimais.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	 
    List<Usuario> findByNome(String usuario);

    Usuario findById(int id);
}