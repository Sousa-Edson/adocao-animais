package com.belval.adocaoanimais.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.belval.adocaoanimais.enums.Especie;
import com.belval.adocaoanimais.enums.Porte;

@Entity
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private Porte porte;
	@Enumerated(EnumType.STRING)
	private Especie especie;
	private int sexo;
	private int vacina;

	private Date nascimento;
	private boolean disponivel;
	@Column(length = 512) // AQUI EU DEFINO O NOME DA COLUNA ,SE PODE NULA E O TAMANHO
	private String resumo;
	@Column(length = 512) // AQUI EU DEFINO O NOME DA COLUNA ,SE PODE NULA E O TAMANHO
	private String observacao;

	@OneToMany(mappedBy = "animal", fetch = FetchType.LAZY) // , fetch = FetchType.EAGER
	private List<Adotar> adotar;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "raca_id", nullable = true)
	private PetRaca petRaca;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cor_id", nullable = true)
	private PetCor petCor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@OneToMany(mappedBy = "animal")
	private List<PetImagem> petImagem;

	public Animal() {

	}

	public Animal(Long id, String nome, Porte porte, Especie especie, int sexo, int vacina, Date nascimento,
			boolean disponivel, String resumo, String observacao) {
		this.id = id;

		this.nome = nome;

		this.porte = porte;
		this.especie = especie;
		this.sexo = sexo;
		this.vacina = vacina;
		this.nascimento = nascimento;
		this.disponivel = disponivel;
		this.resumo = resumo;
		this.observacao = observacao;
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

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Adotar> getAdotar() {
		return adotar;
	}

	public void setAdotar(List<Adotar> adotar) {
		this.adotar = adotar;
	}

	public PetRaca getPetRaca() {
		return petRaca;
	}

	public void setPetRaca(PetRaca petRaca) {
		this.petRaca = petRaca;
	}

	public PetCor getPetCor() {
		return petCor;
	}

	public void setPetCor(PetCor petCor) {
		this.petCor = petCor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PetImagem> getPetImagem() {
		return petImagem;
	}

	public void setPetImagem(List<PetImagem> petImagem) {
		this.petImagem = petImagem;
	}

}
