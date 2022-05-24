package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.adesao.AdesaoNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.resposta.RespostaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.model.Adesao;

public interface AdesaoService {
	
	
	public Adesao cadastrar(Adesao adesao) throws RespostaObrigatoriaException; 

	public Adesao atualizar(Long codigo, Adesao adesao); 
	
	public Adesao buscar(Long codigo) throws AdesaoNaoEncontradaException;
	
	public List<Adesao> listar(); 
	
	public void excluir(Long codigo);

	
}
