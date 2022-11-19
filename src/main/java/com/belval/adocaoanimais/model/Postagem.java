
package com.belval.adocaoanimais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Postagem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String titulo;
	private String conteudo;
	private String caminhoImagem;
	private String dataPublicacao;
	private boolean ativo;

	public Postagem() {
		super();
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}


	public Postagem(Integer id, String titulo, String conteudo, String caminhoImagem, String dataPublicacao,
			boolean ativo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.caminhoImagem = caminhoImagem;
		this.dataPublicacao = dataPublicacao;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
