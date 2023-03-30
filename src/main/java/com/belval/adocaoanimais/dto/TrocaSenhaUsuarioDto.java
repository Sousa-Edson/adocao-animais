package com.belval.adocaoanimais.dto;

import javax.validation.constraints.NotBlank;

import com.belval.adocaoanimais.config.MatchPassword;
import com.belval.adocaoanimais.model.Usuario;

@MatchPassword
public class TrocaSenhaUsuarioDto {
	@NotBlank
	private String senhaAtual;
	@NotBlank
	private String novaSenha;
	@NotBlank
	private String confirmarNovaSenha;

	public TrocaSenhaUsuarioDto() {

	}

	 

	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setSenha(this.novaSenha);
		return usuario;
	}

	public void fromUsuario(Usuario usuario) {
		this.senhaAtual = usuario.getSenha();

	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarNovaSenha() {
		return confirmarNovaSenha;
	}

	public void setConfirmarNovaSenha(String confirmarNovaSenha) {
		this.confirmarNovaSenha = confirmarNovaSenha;
	}

}
