package com.klok.treinamento.adesoes.api.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "resposta")
public class Resposta  implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@NotNull
	@Column(name = "conteudo")
	private String conteudo;
	
	@NotNull
	@JsonIgnoreProperties({"produtos", "respostas"})
	@ManyToOne
	@JoinColumn(name = "codigo_campo")
	private Campo campo;
	
//	@JsonIgnoreProperties("respostas")
//	@ManyToOne
//	@JoinColumn(name = "codigo_adesao")
//	private Adesao adesao;
//	
	
	
	
	
	
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

//	public Adesao getAdesao() {
//		return adesao;
//	}
//
//	public void setAdesao(Adesao adesao) {
//		this.adesao = adesao;
//	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	
	
	
	
	@Override
	public String toString() {
		return "Resposta [codigo=" + codigo + ", conteudo=" + conteudo + ", campo=" + campo + "]";
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
		Resposta other = (Resposta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
	
	
	
}
