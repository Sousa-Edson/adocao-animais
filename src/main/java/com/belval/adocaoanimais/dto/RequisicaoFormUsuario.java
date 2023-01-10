package com.belval.adocaoanimais.dto;

import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.model.Usuario;

public class RequisicaoFormUsuario {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String nascimento;
    private String sexo;
    private String email;
    private String telefone;
    private String telefone2;
    private String senha;

    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    private String caminhoImagem;
    private boolean ativo;

    private Permissao permissao;

    public Usuario toUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNome(this.nome);
        usuario.setSobrenome(this.sobrenome);
        usuario.setCpf(this.cpf);
        usuario.setNascimento(this.nascimento);
        usuario.setSexo(this.sexo);
        usuario.setEmail(this.email);
        usuario.setTelefone(this.telefone);
        usuario.setTelefone2(this.telefone2);
        usuario.setSenha(this.senha);
        usuario.setCep(this.cep);
        usuario.setRua(this.rua);
        usuario.setNumero(this.numero);
        usuario.setBairro(this.bairro);
        usuario.setCidade(this.cidade);
        usuario.setEstado(this.estado);

        return usuario;
    }

    public Usuario toUsuario(Usuario usuario) {

        usuario.setNome(this.nome);
        usuario.setSobrenome(this.sobrenome);
        usuario.setCpf(this.cpf);
        usuario.setNascimento(this.nascimento);
        usuario.setSexo(this.sexo);
        usuario.setEmail(this.email);
        usuario.setTelefone(this.telefone);
        usuario.setTelefone2(this.telefone2);
        usuario.setSenha(this.senha);
        usuario.setCep(this.cep);
        usuario.setRua(this.rua);
        usuario.setNumero(this.numero);
        usuario.setBairro(this.bairro);
        usuario.setCidade(this.cidade);
        usuario.setEstado(this.estado);

        return usuario;
    }

    public void fromUsuario(Usuario usuario) {
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.cpf = usuario.getCpf();
        this.nascimento = usuario.getNascimento();
        this.sexo = usuario.getSexo();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.telefone2 = usuario.getTelefone2();
        this.senha = usuario.getSenha();
        this.cep = usuario.getCep();
        this.rua = usuario.getRua();
        this.numero = usuario.getNumero();
        this.bairro = usuario.getBairro();
        this.cidade = usuario.getCidade();
        this.estado = usuario.getEstado();
    }

    public Usuario toPermissao() {
        Usuario usuario = new Usuario();
        usuario.setPermissao(this.permissao);
        return usuario;
    }

    public Usuario toPermissao(Usuario usuario) {
        usuario.setPermissao(this.permissao);
        return usuario;
    }

    public void fromPermissao(Usuario usuario) {
        this.permissao = usuario.getPermissao();
    }

    public Usuario toUsuarioCheck() {
        Usuario usuario = new Usuario();
        usuario.setAtivo(this.ativo);
        return usuario;
    }

    public Usuario toUsuarioCheck(Usuario usuario) {
        usuario.setAtivo(this.ativo);
        return usuario;
    }

    @Override
    public String toString() {
        return "RequisicaoFormUsuario [nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", nascimento="
                + nascimento + ", sexo=" + sexo + ", email=" + email + ", telefone=" + telefone + ", telefone2="
                + telefone2 + ", senha=" + senha + ", cep=" + cep + ", rua=" + rua + ", numero=" + numero + ", bairro="
                + bairro + ", cidade=" + cidade + ", estado=" + estado + ", caminhoImagem=" + caminhoImagem + ", ativo="
                + ativo + ", permissao=" + permissao + "]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

}
