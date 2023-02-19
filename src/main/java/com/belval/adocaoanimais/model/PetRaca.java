package com.belval.adocaoanimais.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.belval.adocaoanimais.enums.Especie;

@Entity
public class PetRaca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String raca;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especie especie;

    @OneToMany(mappedBy = "petRaca", fetch = FetchType.LAZY) // , fetch = FetchType.EAGER
    private List<Animal> animais;
     

    public PetRaca() {
    }

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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "PetRaca [id=" + id + ", raca=" + raca + ", ativo=" + ativo + ", especie=" + especie + "]";
    }

    

}
