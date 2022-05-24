package com.klok.treinamento.adesoes.api.domain.exceptions.cobertura;

public class CoberturaObrigatoriaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CoberturaObrigatoriaException(String mensagem) {
		super(mensagem);
	}

	public CoberturaObrigatoriaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
