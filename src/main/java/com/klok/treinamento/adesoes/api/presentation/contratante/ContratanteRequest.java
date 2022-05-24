package com.klok.treinamento.adesoes.api.presentation.contratante;

import com.klok.treinamento.adesoes.api.presentation.endereco.EnderecoRequest;

public class ContratanteRequest {

	private Long codigo;
	private String nome;
	private String cpf;
	private String telefone;	
	private EnderecoRequest endereco;

	
	
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public EnderecoRequest getEndereco() {
		return endereco;
	}


	public void setEndereco(EnderecoRequest endereco) {
		this.endereco = endereco;
	}


	
	
	
	
	
	
	
	
	

	
}
