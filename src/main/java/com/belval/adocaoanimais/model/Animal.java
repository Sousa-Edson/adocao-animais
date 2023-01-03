package com.belval.adocaoanimais.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.belval.enums.Especie;
import com.belval.enums.Porte;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userId;
    private String nome;
    private Long raca;
    private Long cor;
    @Enumerated(EnumType.STRING)
    private Porte porte;
    @Enumerated(EnumType.STRING)
    private Especie especie;
    private int sexo;
    private int vacina;

    private Date nascimento;
    private boolean disponivel;
    private String resumo;
    private String observacao;
    

    public Animal() {

    }

    public Animal(Long id, int userId, String nome, Long raca, Long cor, Porte porte, Especie especie, int sexo,
            int vacina, Date nascimento, boolean disponivel, String resumo, String observacao) {
        this.id = id;
        this.userId = userId;
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
        this.porte = porte;
        this.especie = especie;
        this.sexo = sexo;
        this.vacina = vacina;
        this.nascimento = nascimento;
        this.disponivel = disponivel;
        this.resumo = resumo;
        this.observacao = observacao;
    }

     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getRaca() {
        return raca;
    }

    public void setRaca(Long raca) {
        this.raca = raca;
    }

    public Long getCor() {
        return cor;
    }

    public void setCor(Long cor) {
        this.cor = cor;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
