package com.belval.enums;

public enum Porte {
    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande");

    private String descricao;

    Porte(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
