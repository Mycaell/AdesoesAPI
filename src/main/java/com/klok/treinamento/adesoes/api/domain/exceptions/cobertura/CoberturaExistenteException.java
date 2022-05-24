package com.klok.treinamento.adesoes.api.domain.exceptions.cobertura;

public class CoberturaExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CoberturaExistenteException(String mensagem) {
		super(mensagem);
	}

	public CoberturaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
