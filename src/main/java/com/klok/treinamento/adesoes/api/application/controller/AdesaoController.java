package com.klok.treinamento.adesoes.api.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klok.treinamento.adesoes.api.application.event.RecursoCriadoEvent;
import com.klok.treinamento.adesoes.api.application.service.AdesaoServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.adesao.AdesaoNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.resposta.RespostaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.model.Adesao;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.adesao.AdesaoRequest;
import com.klok.treinamento.adesoes.api.presentation.adesao.AdesaoResponse;

@RestController
@RequestMapping(value = "/adesoes")
public class AdesaoController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private AdesaoServiceImpl  adesaoServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	@PostMapping
	public ResponseEntity<AdesaoResponse> cadastrar(@Valid @RequestBody AdesaoRequest adesaoRequest, HttpServletResponse response) throws RespostaObrigatoriaException {

		Adesao adesao = converter.convert(adesaoRequest, Adesao.class);
		
		adesao = adesaoServiceImpl.cadastrar(adesao);
		
		AdesaoResponse adesaoResponse = converter.convert(adesao, AdesaoResponse.class);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, adesao.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(adesaoResponse);
	}

	
	// TODO: atualizar
	

	@GetMapping("/{codigo}")
	public ResponseEntity<AdesaoResponse> buscar(@PathVariable Long codigo) throws AdesaoNaoEncontradaException{
		Adesao adesaoBuscada = adesaoServiceImpl.buscar(codigo);
		
		AdesaoResponse adesaoResponse = converter.convert(adesaoBuscada, AdesaoResponse.class);
		
		return ResponseEntity.ok(adesaoResponse);
	}
	
	
	@GetMapping
	public List<AdesaoResponse> listar(){
		List<Adesao> adesoes = adesaoServiceImpl.listar();
		
		List<AdesaoResponse> listaAdesoesResponse = converter.mapList(adesoes, AdesaoResponse.class);
		
		return listaAdesoesResponse;
	}
	
	
	// TODO: delete
}


