package com.belval.adocaoanimais.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Adotar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private boolean ativo;
	private String propriedade;
	private String casa;
	private String crianca;
	private String acordo;
	private String ausentar;
	private String quintal;
	private String passeios;
	private String animalCasa;
	private boolean declaro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "animalId", nullable = true)
	private Animal animal;

	public Adotar() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getCrianca() {
		return crianca;
	}

	public void setCrianca(String crianca) {
		this.crianca = crianca;
	}

	public String getAcordo() {
		return acordo;
	}

	public void setAcordo(String acordo) {
		this.acordo = acordo;
	}

	public String getAusentar() {
		return ausentar;
	}

	public void setAusentar(String ausentar) {
		this.ausentar = ausentar;
	}

	public String getQuintal() {
		return quintal;
	}

	public void setQuintal(String quintal) {
		this.quintal = quintal;
	}

	public String getPasseios() {
		return passeios;
	}

	public void setPasseios(String passeios) {
		this.passeios = passeios;
	}

	public String getAnimalCasa() {
		return animalCasa;
	}

	public void setAnimalCasa(String animalCasa) {
		this.animalCasa = animalCasa;
	}

	public boolean isDeclaro() {
		return declaro;
	}

	public void setDeclaro(boolean declaro) {
		this.declaro = declaro;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
