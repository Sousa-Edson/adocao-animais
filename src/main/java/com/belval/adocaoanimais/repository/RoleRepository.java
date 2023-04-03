package com.belval.adocaoanimais.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.adocaoanimais.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Collection<Role> findByRole(String role);

}
