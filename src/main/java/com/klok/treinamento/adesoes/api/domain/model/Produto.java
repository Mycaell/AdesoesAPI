package com.klok.treinamento.adesoes.api.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto")
public class Produto  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@JsonIgnoreProperties("produtos")
	@ManyToMany
	@JoinTable(	name = "produto_cobertura",
				joinColumns = @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo"),
				inverseJoinColumns = {@JoinColumn(name = "codigo_cobertura", referencedColumnName = "codigo")})
	private List<Cobertura> coberturas = new ArrayList<>();
	
	@NotNull
	@JsonIgnoreProperties("produtos")
	@ManyToMany
	@JoinTable(	name = "produto_campo",
				joinColumns = @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo"),
				inverseJoinColumns = {@JoinColumn(name = "codigo_campo", referencedColumnName = "codigo")})
	private List<Campo> campos = new ArrayList<>();

	@JsonIgnoreProperties("produto")
	@OneToMany(mappedBy = "produto")
	private List<Adesao> adesoes;
	
	
	
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<Cobertura> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	public List<Adesao> getAdesoes() {
		return adesoes;
	}

	public void setAdesoes(List<Adesao> adesoes) {
		this.adesoes = adesoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
	
}
