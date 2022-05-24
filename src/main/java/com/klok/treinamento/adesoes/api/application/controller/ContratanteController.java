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
import com.klok.treinamento.adesoes.api.application.service.ContratanteServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.contratante.ContratanteExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.contratante.ContratanteNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Contratante;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.contratante.ContratanteRequest;
import com.klok.treinamento.adesoes.api.presentation.contratante.ContratanteResponse;

@RestController
@RequestMapping(value = "/contratantes")
public class ContratanteController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ContratanteServiceImpl contratanteServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	@PostMapping
	public ResponseEntity<ContratanteResponse> cadastrar(@Valid @RequestBody ContratanteRequest contratanteRequest, HttpServletResponse response) throws ContratanteExistenteException {
		
		Contratante contratante = converter.convert(contratanteRequest, Contratante.class);
		
		contratante = contratanteServiceImpl.cadastrar(contratante);
		
		ContratanteResponse contratanteResponse = converter.convert(contratante, ContratanteResponse.class);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, contratante.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contratanteResponse);
	}
	
	
	@PutMapping("/{codigo}") 
	public ResponseEntity<ContratanteResponse> atualizar(@PathVariable Long codigo, @Valid @RequestBody ContratanteRequest contratanteRequest) throws ContratanteNaoEncontradoException, ContratanteExistenteException {
		
		Contratante contratante = converter.convert(contratanteRequest, Contratante.class);
		
		contratante = contratanteServiceImpl.atualizar(codigo, contratante);
		
		ContratanteResponse contratanteResponse = converter.convert(contratante, ContratanteResponse.class);
		
		return ResponseEntity.ok(contratanteResponse);
	}
	
	// TODO: ADD atualização parcial (endereco)
	
	// TODO: ADD atualização parcial (apenas dos dados do contratante)
	
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ContratanteResponse> buscar(@PathVariable Long codigo) throws ContratanteNaoEncontradoException {
		
		Contratante contratanteBuscado = contratanteServiceImpl.buscar(codigo);
		
		ContratanteResponse contratanteResponse = converter.convert(contratanteBuscado, ContratanteResponse.class);
		
		return ResponseEntity.ok(contratanteResponse);
	}
	
	
	@GetMapping
	public List<ContratanteResponse> listar(){
		
		List<Contratante> contratantes = contratanteServiceImpl.listar();
		
		List<ContratanteResponse> listaContratantesResponse = converter.mapList(contratantes, ContratanteResponse.class);
		
		return listaContratantesResponse;
		
	}
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws ContratanteNaoEncontradoException {
		contratanteServiceImpl.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
	
}
