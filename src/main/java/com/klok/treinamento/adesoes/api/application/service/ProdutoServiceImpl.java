package com.klok.treinamento.adesoes.api.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoObrigatorioException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.produto.ProdutoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Campo;
import com.klok.treinamento.adesoes.api.domain.model.Cobertura;
import com.klok.treinamento.adesoes.api.domain.model.Produto;
import com.klok.treinamento.adesoes.api.domain.service.ProdutoService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.CampoRepository;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.CoberturaRepository;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CampoRepository campoRepository;

	@Autowired
	private CoberturaRepository coberturaRepository;

	@Override
	public Produto cadastrar(Produto produto) throws CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException {

		produto = prepararObjeto(produto);
		
		return produtoRepository.save(produto);
	}


	@Override
	public Produto atualizar(Long codigo, Produto produto) throws ProdutoNaoEncontradoException, CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException {
		
		Optional<Produto> produtoOptional = produtoRepository.findById(codigo);

		if(produtoOptional.isEmpty()) {
			throw new ProdutoNaoEncontradoException("Não foi encontrado um produto com o id: " + codigo);
		}
		
		
		Produto produtoDoBanco = produtoOptional.get();
			
		produto = prepararObjeto(produto);
		
		BeanUtils.copyProperties(produto, produtoDoBanco, "codigo");

		return produtoRepository.save(produtoDoBanco);
	}

	
	@Override
	public Produto buscar(Long codigo) throws ProdutoNaoEncontradoException {
		Optional<Produto> produtoOptional = produtoRepository.findById(codigo);

		if(produtoOptional.isEmpty()) {
			throw new ProdutoNaoEncontradoException("Não foi encontrado um produto com o id: " + codigo);
		}
		
		return produtoOptional.get();
	}
	
	
	@Override
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	
	@Override
	public void excluir(Long codigo) throws ProdutoNaoEncontradoException{
		
		Optional<Produto> produtoOptional = produtoRepository.findById(codigo);

		if(produtoOptional.isEmpty()) {
			throw new ProdutoNaoEncontradoException("Não foi encontrado um produto com o id: " + codigo);
		}
		
		
		produtoRepository.deleteById(codigo);
	}
	
	
	
	
	
	private Produto prepararObjeto(Produto produto) throws CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException {
		List<Campo> campos = new ArrayList<>();
		List<Cobertura> coberturas = new ArrayList<>();
		
		if(produto.getCampos() == null || produto.getCampos().size() == 0) {
			throw new CampoObrigatorioException("Para cadastar um produto é necessário informar um ou mais campos.");
		}
		
		if(produto.getCoberturas() == null || produto.getCoberturas().size() == 0) {
			throw new CoberturaObrigatoriaException("Para cadastar um produto é necessário informar um ou mais coberturas.");
		}
		
		
		for (Campo campo : produto.getCampos()) {

			Optional<Campo> campoBuscado = campoRepository.findById(campo.getCodigo());

			if (campoBuscado.isEmpty()) {
				throw new CampoNaoEncontradoException("Não foi encontrado um campo com o id: " + campo.getCodigo());
			}
			
			campos.add(campoBuscado.get());
		}

		for (Cobertura cobertura : produto.getCoberturas()) {

			Optional<Cobertura> coberturaBuscada = coberturaRepository.findById(cobertura.getCodigo());

			if (coberturaBuscada.isEmpty()) {
				throw new CoberturaNaoEncontradaException("Não foi encontrada nenhuma cobertura com o id: " + cobertura.getCodigo());
			}
			
			coberturas.add(coberturaBuscada.get());
		}

		produto.setCampos(campos);
		produto.setCoberturas(coberturas);
		
		return produto;
	}

}
