package com.klok.treinamento.adesoes.api.domain.exceptions.adesao;

public class AdesaoNaoEncontradaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AdesaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public AdesaoNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
