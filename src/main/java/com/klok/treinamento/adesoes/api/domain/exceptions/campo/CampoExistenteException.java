package com.klok.treinamento.adesoes.api.domain.exceptions.campo;

public class CampoExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CampoExistenteException(String mensagem) {
		super(mensagem);
	}

	public CampoExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
