package com.klok.treinamento.adesoes.api.domain.exceptions.usuario;

public class UsuarioNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
