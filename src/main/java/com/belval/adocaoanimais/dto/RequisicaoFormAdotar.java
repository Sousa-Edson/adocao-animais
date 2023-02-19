package com.belval.adocaoanimais.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.belval.adocaoanimais.model.Adotar;

public class RequisicaoFormAdotar {

    private boolean ativo;
    @NotBlank
    @NotNull
    private String propriedade;
    @NotBlank
    @NotNull
    private String casa;
    @NotBlank
    @NotNull
    private String crianca;
    @NotBlank
    @NotNull
    private String acordo;
    @NotBlank
    @NotNull
    private String ausentar;
    @NotBlank
    @NotNull
    private String quintal;
    @NotBlank
    @NotNull
    private String passeios;
    @NotBlank
    @NotNull
    private String animalCasa;
    @NotNull
@AssertTrue
    private boolean declaro;


    

    
    public RequisicaoFormAdotar() {
    }

    public Adotar toAdotar() {
        Adotar adotar = new Adotar();
        adotar.setAtivo(this.ativo);
        adotar.setPropriedade(this.propriedade);
        adotar.setCasa(this.animalCasa);
        adotar.setCrianca(this.crianca);
        adotar.setAcordo(this.acordo);
        adotar.setAusentar(this.ausentar);
        adotar.setQuintal(this.quintal);
        adotar.setPasseios(this.passeios);
        adotar.setAnimalCasa(this.animalCasa);
        adotar.setDeclaro(this.declaro);
        return adotar;
    }

    public Adotar toAdotar(Adotar adotar) {
        adotar.setAtivo(this.ativo);
        adotar.setPropriedade(this.propriedade);
        adotar.setCasa(this.animalCasa);
        adotar.setCrianca(this.crianca);
        adotar.setAcordo(this.acordo);
        adotar.setAusentar(this.ausentar);
        adotar.setQuintal(this.quintal);
        adotar.setPasseios(this.passeios);
        adotar.setAnimalCasa(this.animalCasa);
        adotar.setDeclaro(this.declaro);
        return adotar;
    }

    public void fromAdotar(Adotar adotar) {
        this.ativo = adotar.isAtivo();
        this.propriedade = adotar.getPropriedade();
        this.casa = adotar.getCasa();
        this.crianca = adotar.getCrianca();
        this.acordo = adotar.getAcordo();
        this.ausentar = adotar.getAusentar();
        this.quintal = adotar.getQuintal();
        this.passeios = adotar.getPasseios();
        this.animalCasa = adotar.getAnimalCasa();
        this.declaro = adotar.isDeclaro();
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

}
