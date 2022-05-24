package com.klok.treinamento.adesoes.api.presentation.contratante;

import com.klok.treinamento.adesoes.api.presentation.endereco.EnderecoResponse;

public class ContratanteResponse {
	

	private Long codigo;
	private String nome;
	private String cpf;
	private String telefone;
	private EnderecoResponse endereco;

	
	
	



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

	public EnderecoResponse getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResponse endereco) {
		this.endereco = endereco;
	}


	
	
	

	
	
	
	
	


}
