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
import com.klok.treinamento.adesoes.api.application.service.CoberturaServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.model.Cobertura;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.cobertura.CoberturaRequest;
import com.klok.treinamento.adesoes.api.presentation.cobertura.CoberturaResponse;

@RestController
@RequestMapping(value = "/coberturas")
public class CoberturaController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CoberturaServiceImpl coberturaServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	
	@PostMapping
	public ResponseEntity<CoberturaResponse> cadastrar(@Valid @RequestBody CoberturaRequest coberturaRequest, HttpServletResponse response) throws CoberturaExistenteException {
		
		Cobertura cobertura = converter.convert(coberturaRequest, Cobertura.class);
		
		cobertura = coberturaServiceImpl.cadastrar(cobertura);
		
		CoberturaResponse coberturaResponse = converter.convert(cobertura, CoberturaResponse.class);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cobertura.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(coberturaResponse);
	}
	
	
	@PutMapping("/{codigo}") 
	public ResponseEntity<CoberturaResponse> atualizar(@PathVariable Long codigo, @Valid @RequestBody CoberturaRequest coberturaRequest) throws CoberturaNaoEncontradaException, CoberturaExistenteException{
		
		Cobertura cobertura = converter.convert(coberturaRequest, Cobertura.class);
		
		cobertura = coberturaServiceImpl.atualizar(codigo, cobertura);
		
		CoberturaResponse coberturaResponse = converter.convert(cobertura, CoberturaResponse.class);
		
		return ResponseEntity.ok(coberturaResponse);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<CoberturaResponse> buscar(@PathVariable Long codigo) throws CoberturaNaoEncontradaException{
		Cobertura coberturaBuscada = coberturaServiceImpl.buscar(codigo);
		
		CoberturaResponse coberturaResponse = converter.convert(coberturaBuscada, CoberturaResponse.class);
		
		return ResponseEntity.ok(coberturaResponse);
	}
	
	
	@GetMapping
	public List<CoberturaResponse> listar(){
		List<Cobertura> coberturas = coberturaServiceImpl.listar();
		
		List<CoberturaResponse> listaCoberturasResponse = converter.mapList(coberturas, CoberturaResponse.class);
			
		return listaCoberturasResponse;
	}
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws CoberturaNaoEncontradaException{
		coberturaServiceImpl.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
	
}
