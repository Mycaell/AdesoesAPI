package com.klok.treinamento.adesoes.api.domain.exceptions.usuario;

public class UsuarioExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String mensagem) {
		super(mensagem);
	}

	public UsuarioExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
