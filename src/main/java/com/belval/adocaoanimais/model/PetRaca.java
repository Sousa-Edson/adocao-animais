package com.belval.adocaoanimais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PetRaca {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String raca;
    private boolean ativo;

    public PetRaca(int id, String raca, boolean ativo) {
        super();
        this.id = id;
        this.raca = raca;
        this.ativo = ativo;
    }

    public PetRaca() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
