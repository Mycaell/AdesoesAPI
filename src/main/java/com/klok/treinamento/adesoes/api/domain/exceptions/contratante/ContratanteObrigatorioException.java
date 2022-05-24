package com.klok.treinamento.adesoes.api.domain.exceptions.contratante;

public class ContratanteObrigatorioException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContratanteObrigatorioException(String mensagem) {
		super(mensagem);
	}

	public ContratanteObrigatorioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
