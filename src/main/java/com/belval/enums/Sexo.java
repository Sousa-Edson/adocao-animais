package com.belval.enums;

public enum Sexo {
    MANHA("manhã"),
    TARDE("tarde"),
    NOITE("noite");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
