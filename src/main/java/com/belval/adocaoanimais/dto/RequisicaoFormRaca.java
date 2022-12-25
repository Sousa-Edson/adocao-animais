package com.belval.adocaoanimais.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.belval.adocaoanimais.model.PetRaca;
import com.belval.enums.Especie;

public class RequisicaoFormRaca {
    @NotBlank
    @NotNull
    private String raca;
    private boolean ativo;
    private Especie especie;

    public PetRaca toPetRaca() {
        PetRaca petRaca = new PetRaca();
        petRaca.setRaca(this.raca);
        petRaca.setAtivo(this.ativo);
        petRaca.setEspecie(this.especie);
        return petRaca;
    }

    public PetRaca toPetRaca(PetRaca petRaca) {
        petRaca.setRaca(this.raca);
        petRaca.setAtivo(this.ativo);
        petRaca.setEspecie(this.especie);
        return petRaca;
    }

    public void fromPetRaca(PetRaca petRaca) {
        this.raca = petRaca.getRaca();
        this.ativo = petRaca.isAtivo();
        this.especie = petRaca.getEspecie();
    }

    public PetRaca toPetRacaCheck() {
        PetRaca petRaca = new PetRaca();
        petRaca.setAtivo(this.ativo);
        return petRaca;
    }

    public PetRaca toPetRacaCheck(PetRaca petRaca) {
        petRaca.setAtivo(this.ativo);
        return petRaca;
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

    @Override
    public String toString() {
        return "RequisicaoFormRaca [raca=" + raca + ", ativo=" + ativo + ", especie=" + especie + "]";
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

}
