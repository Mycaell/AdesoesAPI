package com.klok.treinamento.adesoes.api.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klok.treinamento.adesoes.api.application.service.CobrancaServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobranca.CobrancaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.model.Cobranca;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.cobranca.CobrancaResponse;

@RestController
@RequestMapping(value = "/cobrancas")
public class CobrancaController {

	
	@Autowired
	private CobrancaServiceImpl  cobrancaServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	
	
	
	// TODO: atualizar
	

	@GetMapping("/{codigo}")
	public ResponseEntity<CobrancaResponse> buscar(@PathVariable Long codigo) throws CobrancaNaoEncontradaException{
		Cobranca cobrancaBuscada = cobrancaServiceImpl.buscar(codigo);
		
		CobrancaResponse adesaoResponse = converter.convert(cobrancaBuscada, CobrancaResponse.class);
		
		return ResponseEntity.ok(adesaoResponse);
	}
	
	
	@GetMapping
	public List<CobrancaResponse> listar(){
		List<Cobranca> cobrancas = cobrancaServiceImpl.listar();
		
		List<CobrancaResponse> listaCobrancasResponse = converter.mapList(cobrancas, CobrancaResponse.class);
		
		return listaCobrancasResponse;
	}
	
	
	// TODO: delete
}


