package com.klok.treinamento.adesoes.api.domain.exceptions.papel;

public class PapelNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PapelNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PapelNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
