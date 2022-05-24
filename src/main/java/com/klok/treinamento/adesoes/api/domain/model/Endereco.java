package com.klok.treinamento.adesoes.api.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;


@Embeddable
public class Endereco  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "estado")
	private String estado;

	@NotBlank
	@Column(name = "cidade")
	private String cidade;
	
	@NotBlank
	@Column(name = "bairro")
	private String bairro;
	
	@NotBlank
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "numero_casa")
	private Integer numeroCasa;
	
	
	
	
	
	
	

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumeroCasa() {
		return numeroCasa;
	}
	public void setNumeroCasa(Integer numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	
	
	
	
	
}
