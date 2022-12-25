package com.belval.adocaoanimais.model;

 


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.belval.enums.Especie;
import com.belval.enums.Porte;
 

@Entity
 
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private String nome;
	private int raca;
	private int cor;
    @Enumerated(EnumType.STRING)
	private Porte porte;
    @Enumerated(EnumType.STRING)
	private Especie especie;
	private int sexo;
	private int vacina; 
    @DateTimeFormat 
	private LocalDate nascimento;

	 
	public Animal() {
		super();
	}


    public Animal(int id, int userId, String nome, int raca, int cor, Porte porte, Especie especie, int sexo, int vacina,
    LocalDate nascimento) {
		this.id = id;
		this.userId = userId;
		this.nome = nome;
		this.raca = raca;
		this.cor = cor;
		 
		this.sexo = sexo;
		this.vacina = vacina;
		this.nascimento = nascimento;
	}


	public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
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


    public LocalDate getNascimento() {
        return nascimento;
    }


    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }


    public Porte getPorte() {
        return porte;
    }


    public Especie getEspecie() {
        return especie;
    }


	 
	

	 

}
