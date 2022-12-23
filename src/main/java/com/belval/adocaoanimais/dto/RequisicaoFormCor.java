package com.belval.adocaoanimais.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.belval.adocaoanimais.model.PetCor;

public class RequisicaoFormCor {
    @NotBlank
    @NotNull
    private String cor;
    private boolean ativo;

    public PetCor toPetCor() {
        PetCor petCor = new PetCor();
        petCor.setCor(this.cor);
        petCor.setAtivo(this.ativo);
        return petCor;
    }

    public PetCor toPetCor(PetCor petCor) {
        petCor.setCor(this.cor);
        petCor.setAtivo(this.ativo);
        return petCor;
    }

    public void fromPetCor(PetCor petCor) {
        this.cor = petCor.getCor();
        this.ativo = petCor.isAtivo();
    }

    public PetCor toPetCorCheck() {
        PetCor petCor = new PetCor();
        petCor.setAtivo(this.ativo);
        return petCor;
    }

    public PetCor toPetCorCheck(PetCor petCor) {
        petCor.setAtivo(this.ativo);
        return petCor;
    }

    @Override
    public String toString() {
        return "RequisicaoFormCor [cor=" + cor + ", ativo=" + ativo + "]";
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
