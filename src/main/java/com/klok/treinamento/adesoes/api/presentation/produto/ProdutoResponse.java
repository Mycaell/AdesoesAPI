package com.klok.treinamento.adesoes.api.presentation.produto;

import java.util.List;

import com.klok.treinamento.adesoes.api.presentation.campo.CampoResponse;
import com.klok.treinamento.adesoes.api.presentation.cobertura.CoberturaResponse;

public class ProdutoResponse {
	
	private Long codigo;
	private List<CoberturaResponse> coberturas;
	private List<CampoResponse> campos;
	
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<CoberturaResponse> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(List<CoberturaResponse> coberturas) {
		this.coberturas = coberturas;
	}

	public List<CampoResponse> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoResponse> campos) {
		this.campos = campos;
	}

	
	
	


}
