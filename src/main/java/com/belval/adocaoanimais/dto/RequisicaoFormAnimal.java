package com.belval.adocaoanimais.dto;
  


import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.belval.adocaoanimais.model.Animal;
import com.belval.enums.Especie;
import com.belval.enums.Porte;
 

public class RequisicaoFormAnimal {
    @NotBlank
    @NotNull
    private String nome;
    private int raca;
    private int cor; 
    private Porte porte; 
    private Especie especie;
 
    private int sexo;
    private int vacina; 
    @DateTimeFormat
    private LocalDate nascimento;

    public Animal toAnimal() {
        Animal animal = new Animal();
        animal.setNome(this.nome);
        animal.setRaca(this.raca);
        animal.setCor(this.cor);
      
        animal.setSexo(this.sexo);
        animal.setVacina(this.vacina);
        // animal.setNascimento(this.nascimento);
        return animal;
    }

    public Animal toAnimal(Animal animal) {
        animal.setNome(this.nome);
        animal.setRaca(this.raca);
        animal.setCor(this.cor);
        
        animal.setSexo(this.sexo);
        animal.setVacina(this.vacina);
        // animal.setNascimento(this.nascimento);
        return animal;
    }

    public void fromAnimal(Animal animal) {
        this.nome = animal.getNome();
        this.raca = animal.getRaca();
        this.cor = animal.getCor();
        
        this.sexo = animal.getSexo();
        this.vacina = animal.getVacina();
        // this.nascimento = animal.getNascimento();
    }

    @Override
    public String toString() {
        return "RequisicaoFormAnimal [nome=" + nome + ", raca=" + raca + ", cor=" + cor + ", porte=" + porte
                + ", especie=" + especie + ", sexo=" + sexo + ", vacina=" + vacina + ", nascimento=" + nascimento + "]";
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
 
}
