package com.klok.treinamento.adesoes.api.domain.exceptions.campo;

public class CampoNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CampoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CampoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
