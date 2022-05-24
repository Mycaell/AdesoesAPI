package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.papel.PapelExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.papel.PapelNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Papel;

public interface PapelService {	
	
	public Papel cadastrar(Papel papel) throws PapelExistenteException; 
	
	public Papel atualizar(Long codigo, Papel papel) throws PapelNaoEncontradoException, PapelExistenteException; 
	
	public Papel buscar(Long codigo) throws PapelNaoEncontradoException; 
	
	public List<Papel> listar();
	
	public void excluir(Long codigo) throws PapelNaoEncontradoException;
	
}
