package com.klok.treinamento.adesoes.api.domain.exceptions.cobranca;

public class CobrancaNaoEncontradaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CobrancaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CobrancaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
