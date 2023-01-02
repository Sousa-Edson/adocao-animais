package com.belval.adocaoanimais.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;

import com.belval.adocaoanimais.model.Postagem;

public class RequisicaoFormPostagem {
    @NotBlank
    @NotNull
    private String titulo;
    private String conteudo;
    // private String caminhoImagem;
    private String dataPublicacao;
    private URL linkEvento;
    private boolean ativo;

    public Postagem toPostagem() {
        Postagem postagem = new Postagem();
        postagem.setTitulo(this.titulo);
        postagem.setConteudo(this.conteudo);
        // postagem.setCaminhoImagem(this.caminhoImagem);
        postagem.setDataPublicacao(this.dataPublicacao);
        postagem.setLinkEvento( this.linkEvento);
        postagem.setAtivo(this.ativo);
        return postagem;
    }

    public Postagem toPostagem(Postagem postagem) {
        postagem.setTitulo(this.titulo);
        postagem.setConteudo(this.conteudo);
        // postagem.setCaminhoImagem(this.caminhoImagem);
        postagem.setDataPublicacao(this.dataPublicacao);
        postagem.setLinkEvento( this.linkEvento);
        postagem.setAtivo(this.ativo);
        return postagem;
    }

    public void fromPostagem(Postagem postagem) {
        this.titulo = postagem.getTitulo();
        this.conteudo = postagem.getConteudo();
        // this.caminhoImagem = postagem.getCaminhoImagem();
        this.dataPublicacao = postagem.getDataPublicacao();
        this.linkEvento = postagem.getLinkEvento();
        this.ativo = postagem.isAtivo();
    }

    public Postagem toPostagemCheck() {
        Postagem postagem = new Postagem();
        postagem.setAtivo(this.ativo);
        return postagem;
    }

    public Postagem toPostagemCheck(Postagem postagem) {
        postagem.setAtivo(this.ativo);
        return postagem;
    }

    
    @Override
    public String toString() {
        return "RequisicaoFormPostagem [titulo=" + titulo + ", conteudo=" + conteudo + ", dataPublicacao="
                + dataPublicacao + ", ativo=" + ativo + "]";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public URL getLinkEvento() {
        return linkEvento;
    }

    public void setLinkEvento(URL linkEvento) {
        this.linkEvento = linkEvento;
    }
    

}
