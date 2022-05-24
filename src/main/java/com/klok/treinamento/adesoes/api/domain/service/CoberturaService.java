package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.model.Cobertura;

public interface CoberturaService {	
	
	public Cobertura cadastrar(Cobertura cobertura) throws CoberturaExistenteException; 
	
	public Cobertura atualizar(Long codigo, Cobertura cobertura) throws CoberturaNaoEncontradaException, CoberturaExistenteException; 
	
	public Cobertura buscar(Long codigo) throws CoberturaNaoEncontradaException; 
	
	public List<Cobertura> listar();
	
	public void excluir(Long codigo) throws CoberturaNaoEncontradaException;
	
}
