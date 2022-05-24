package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoObrigatorioException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.produto.ProdutoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Produto;

public interface ProdutoService {
	
	
	public Produto cadastrar(Produto produto) throws CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException ; 

	public Produto atualizar(Long codigo, Produto produto) throws ProdutoNaoEncontradoException, CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException ; 
	
	public Produto buscar(Long codigo) throws ProdutoNaoEncontradoException;
	
	public List<Produto> listar(); 
	
	public void excluir(Long codigo) throws ProdutoNaoEncontradoException;

	
}
