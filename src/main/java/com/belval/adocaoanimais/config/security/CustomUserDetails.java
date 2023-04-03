package com.belval.adocaoanimais.config.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.belval.adocaoanimais.model.Role;
import com.belval.adocaoanimais.model.Usuario;

public class CustomUserDetails implements UserDetails {

	private Usuario user;

	public CustomUserDetails(Usuario user) {
		this.user = user;
	}

//	@Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//		System.out.println("auth: "+user.getRoles().toString().replace("[", "").replace("]", ""));
//        return Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString().replace("[", "").replace("]", "")));
//    }

	@Override
	public String getPassword() {
		return user.getSenha();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAuthories(user);
	}

	private Set<GrantedAuthority> getAuthories(Usuario user) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
		}
		System.out.println("auth: " + user.getRoles());
		return authorities;
	}
}
