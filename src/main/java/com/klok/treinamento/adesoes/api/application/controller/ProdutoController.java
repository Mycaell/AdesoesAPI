package com.klok.treinamento.adesoes.api.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klok.treinamento.adesoes.api.application.event.RecursoCriadoEvent;
import com.klok.treinamento.adesoes.api.application.service.ProdutoServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoObrigatorioException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.produto.ProdutoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Produto;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.produto.ProdutoRequest;
import com.klok.treinamento.adesoes.api.presentation.produto.ProdutoResponse;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Autowired
	private ProdutoServiceImpl  produtoServiceImpl;
	
	
	@Autowired
	private ConverterServiceImpl converter;
	
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody ProdutoRequest produtoRequest, HttpServletResponse response) throws CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException{
		
		Produto produto = converter.convert(produtoRequest, Produto.class);
		
		produto = produtoServiceImpl.cadastrar(produto);
		
		ProdutoResponse produtoResponse = converter.convert(produto, ProdutoResponse.class);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produto.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
	}

	
	@PutMapping("/{codigo}") 
	public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long codigo, @Valid @RequestBody ProdutoRequest produtoRequest) throws ProdutoNaoEncontradoException, CampoNaoEncontradoException, CoberturaNaoEncontradaException, CampoObrigatorioException, CoberturaObrigatoriaException{
		
		Produto produto = converter.convert(produtoRequest, Produto.class);
		
		produto = produtoServiceImpl.atualizar(codigo, produto);
		
		ProdutoResponse produtoResponse = converter.convert(produto, ProdutoResponse.class);
		
		return ResponseEntity.ok(produtoResponse);
	}

	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoResponse> buscar(@PathVariable Long codigo) throws ProdutoNaoEncontradoException{
		Produto produtoBuscado = produtoServiceImpl.buscar(codigo);
		
		ProdutoResponse produtoResponse = converter.convert(produtoBuscado, ProdutoResponse.class);
		
		return ResponseEntity.ok(produtoResponse);
	}
	
	
	@GetMapping
	public List<ProdutoResponse> listar(){
		List<Produto> produtos = produtoServiceImpl.listar();
		
		List<ProdutoResponse> listaProdutosResponse = converter.mapList(produtos, ProdutoResponse.class);
		
		return listaProdutosResponse;
	}
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws ProdutoNaoEncontradoException{
		produtoServiceImpl.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
	
	
}
