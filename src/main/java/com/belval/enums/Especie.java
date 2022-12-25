package com.belval.enums;

public enum Especie {
    CACHORRO("Cachorro"),
    GATO("Gato"),
    OUTRO("Outro");

    private String descricao;

    Especie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
