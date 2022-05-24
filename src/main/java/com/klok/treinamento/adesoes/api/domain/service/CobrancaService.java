package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.cobranca.CobrancaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.model.Adesao;
import com.klok.treinamento.adesoes.api.domain.model.Cobranca;

public interface CobrancaService {
	
	public Cobranca gerarCobranca(Adesao adesao);
	
	public void cadastrar(Cobranca cobranca);
	
	public void atualizar(Cobranca cobrancaBuscada, Cobranca cobranca);
	
	public void gerarCobrancasRecorrentes();

	
	public Cobranca buscar(Long codigo) throws CobrancaNaoEncontradaException;
	
	public List<Cobranca> listar(); 
	
}
