package com.belval.adocaoanimais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findOneById(Long id);
	Usuario findByEmail(String username);
//	Usuario findByUsername(String username);
   
}