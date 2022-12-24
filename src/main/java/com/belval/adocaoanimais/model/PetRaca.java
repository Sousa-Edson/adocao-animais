package com.belval.adocaoanimais.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.belval.enums.TipoAnimal;

@Entity
public class PetRaca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String raca;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoAnimal tipoAnimal;

     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    @Override
    public String toString() {
        return "PetRaca [id=" + id + ", raca=" + raca + ", ativo=" + ativo + ", tipoAnimal=" + tipoAnimal + "]";
    }

}
