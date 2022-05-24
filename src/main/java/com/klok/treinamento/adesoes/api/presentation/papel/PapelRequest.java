package com.klok.treinamento.adesoes.api.presentation.papel;

import com.klok.treinamento.adesoes.api.domain.enums.TipoPapel;

public class PapelRequest {

	private Long codigo;
	private TipoPapel nome;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public TipoPapel getNome() {
		return nome;
	}
	public void setNome(TipoPapel nome) {
		this.nome = nome;
	}
	

	
	
	
	
	
	
}
