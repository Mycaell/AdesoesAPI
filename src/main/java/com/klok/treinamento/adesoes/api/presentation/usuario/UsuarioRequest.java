package com.klok.treinamento.adesoes.api.presentation.usuario;

import com.klok.treinamento.adesoes.api.presentation.papel.PapelRequest;

public class UsuarioRequest {

	private Long codigo;
	private String nome;
	private String login;
	private String senha;
	private PapelRequest papel;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public PapelRequest getPapel() {
		return papel;
	}
	public void setPapel(PapelRequest papel) {
		this.papel = papel;
	}
	
	
	
	
	
	
	
	
}
