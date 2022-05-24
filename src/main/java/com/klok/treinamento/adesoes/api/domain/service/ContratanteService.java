package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.contratante.ContratanteExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.contratante.ContratanteNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Contratante;

public interface ContratanteService {	
	
	public Contratante cadastrar(Contratante contratante) throws ContratanteExistenteException; 
	
	public Contratante atualizar(Long codigo, Contratante contratante) throws ContratanteNaoEncontradoException, ContratanteExistenteException ; 
	
	public Contratante buscar(Long codigo) throws ContratanteNaoEncontradoException; 
	
	public List<Contratante> listar();
	
	public void excluir(Long codigo) throws ContratanteNaoEncontradoException;
	
}
