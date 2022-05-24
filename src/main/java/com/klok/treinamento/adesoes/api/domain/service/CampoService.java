package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Campo;

public interface CampoService {	
	
	public Campo cadastrar(Campo campo) throws CampoExistenteException; 
	
	public Campo atualizar(Long codigo, Campo campo) throws CampoNaoEncontradoException, CampoExistenteException; 
	
	public Campo buscar(Long codigo) throws CampoNaoEncontradoException; 
	
	public List<Campo> listar();
	
	public void excluir(Long codigo) throws CampoNaoEncontradoException;
	
}
