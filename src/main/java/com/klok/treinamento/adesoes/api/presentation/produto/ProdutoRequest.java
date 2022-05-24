package com.klok.treinamento.adesoes.api.presentation.produto;

import java.util.List;

import com.klok.treinamento.adesoes.api.presentation.campo.CampoRequest;
import com.klok.treinamento.adesoes.api.presentation.cobertura.CoberturaRequest;

public class ProdutoRequest {

	private Long codigo;
	private List<CoberturaRequest> coberturas;
	private List<CampoRequest> campos;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public List<CoberturaRequest> getCoberturas() {
		return coberturas;
	}
	public void setCoberturas(List<CoberturaRequest> coberturas) {
		this.coberturas = coberturas;
	}
	public List<CampoRequest> getCampos() {
		return campos;
	}
	public void setCampos(List<CampoRequest> campos) {
		this.campos = campos;
	}

	
	
	
	
	
	
}
