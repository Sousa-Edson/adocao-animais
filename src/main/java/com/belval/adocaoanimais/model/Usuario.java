package com.belval.adocaoanimais.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.belval.adocaoanimais.enums.Permissao;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
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

	@Enumerated(EnumType.STRING)
	private Permissao permissao;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY) // , fetch = FetchType.EAGER
	private List<Animal> animais;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	

	public Usuario() {

	}
	

	public Usuario(String nome, String sobrenome, String cpf, String nascimento, String sexo, String email,
			String telefone, String senha,  Permissao permissao,boolean ativo, Collection<Role> roles) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.ativo = ativo;
		this.permissao = permissao;
		this.roles = roles;
	}


	public Usuario(String nome, String sobrenome, String cpf, String nascimento, String sexo, String email,
			String telefone, String senha, Permissao permissao, boolean ativo) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.permissao = permissao;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

 
	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) { 
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.senha = passwordEncoder.encode(senha);
	}

}
