package com.klok.treinamento.adesoes.api.domain.exceptions.produto;

public class ProdutoNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
