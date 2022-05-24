package com.klok.treinamento.adesoes.api.domain.exceptions.cobertura;

public class CoberturaNaoEncontradaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CoberturaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CoberturaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
