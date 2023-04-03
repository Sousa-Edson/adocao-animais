package com.belval.adocaoanimais.config.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.belval.adocaoanimais.model.Role;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

	private UsuarioRepository userRepository;

	public SSUserDetailsService(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  System.out.println("Email: "+username);
		  String email = username;
	        Usuario user = userRepository.findByEmail(username);
	        if (user == null) {
	        	System.out.println("username: "+username);
	        	 System.out.println("USUARIO errado: "+user);
	            throw new UsernameNotFoundException("Usuário não encontrado");
	            
	        }
	        System.out.println("USUARIO certo: "+user.getRoles());
	        return (UserDetails) new CustomUserDetails(user);
	    }

//	private Set<GrantedAuthority> getAuthories(Usuario user) {
//
//		Set<GrantedAuthority> authorities = new HashSet<>();
//		for (Role role : user.getRoles()) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
//			authorities.add(grantedAuthority);
//		}
//		  System.out.println("auth: "+user.getRoles());
//		return authorities;
//	}
	
	
}