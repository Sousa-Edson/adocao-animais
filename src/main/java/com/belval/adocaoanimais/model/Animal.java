package com.belval.adocaoanimais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	private int raca;
	private int cor;
	private int porte;
	private String especie;
	private int sexo;
	private int vacina;

	public Animal(int id, String nome, int raca, int cor, int porte, String especie, int sexo, int vacina) {
		super();
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.cor = cor;
		this.porte = porte;
		this.especie = especie;
		this.sexo = sexo;
		this.vacina = vacina;
	}

	public Animal() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRaca() {
		return raca;
	}

	public void setRaca(int raca) {
		this.raca = raca;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public int getPorte() {
		return porte;
	}

	public void setPorte(int porte) {
		this.porte = porte;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public int getVacina() {
		return vacina;
	}

	public void setVacina(int vacina) {
		this.vacina = vacina;
	}

}
