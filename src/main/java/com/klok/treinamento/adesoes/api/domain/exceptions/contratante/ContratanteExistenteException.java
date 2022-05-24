package com.klok.treinamento.adesoes.api.domain.exceptions.contratante;

public class ContratanteExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContratanteExistenteException(String mensagem) {
		super(mensagem);
	}

	public ContratanteExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
