package com.klok.treinamento.adesoes.api.domain.exceptions.resposta;

public class RespostaObrigatoriaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RespostaObrigatoriaException(String mensagem) {
		super(mensagem);
	}

	public RespostaObrigatoriaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
