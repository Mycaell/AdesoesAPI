package com.klok.treinamento.adesoes.api.domain.exceptions.papel;

public class PapelExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PapelExistenteException(String mensagem) {
		super(mensagem);
	}

	public PapelExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
