package com.klok.treinamento.adesoes.api.domain.exceptions.campo;

public class CampoObrigatorioException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CampoObrigatorioException(String mensagem) {
		super(mensagem);
	}

	public CampoObrigatorioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
