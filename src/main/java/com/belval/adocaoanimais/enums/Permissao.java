package com.belval.adocaoanimais.enums;

public enum Permissao {
    ADMINISTRADOR("Administrador"),
    COLABORADOR("Colaborador"),
    SUPORTE("Suporte"),
    USUARIO("Usuário");

    private String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
