package com.klok.treinamento.adesoes.api.domain.exceptions.contratante;

public class ContratanteNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContratanteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ContratanteNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
