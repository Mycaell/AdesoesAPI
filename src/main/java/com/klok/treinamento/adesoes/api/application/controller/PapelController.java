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
import com.klok.treinamento.adesoes.api.application.service.PapelServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.papel.PapelExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.papel.PapelNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Papel;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.papel.PapelRequest;
import com.klok.treinamento.adesoes.api.presentation.papel.PapelResponse;

@RestController
@RequestMapping(value = "/papeis")
public class PapelController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PapelServiceImpl papelServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	
	@PostMapping
	public ResponseEntity<PapelResponse> cadastrar(@Valid @RequestBody PapelRequest papelRequest, HttpServletResponse response) throws PapelExistenteException {
		
		Papel papel = converter.convert(papelRequest, Papel.class);
		
		papel = papelServiceImpl.cadastrar(papel);
		
		PapelResponse papelResponse = converter.convert(papel, PapelResponse.class);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, papel.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(papelResponse);
	}
	
	
	@PutMapping("/{codigo}") 
	public ResponseEntity<PapelResponse> atualizar(@PathVariable Long codigo, @Valid @RequestBody PapelRequest papelRequest) throws PapelNaoEncontradoException, PapelExistenteException {
		
		Papel papel = converter.convert(papelRequest, Papel.class);
		
		papel = papelServiceImpl.atualizar(codigo, papel);
		
		PapelResponse papelResponse = converter.convert(papel, PapelResponse.class);
		
		return ResponseEntity.ok(papelResponse);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<PapelResponse> buscar(@PathVariable Long codigo) throws PapelNaoEncontradoException {
		Papel papelBuscado = papelServiceImpl.buscar(codigo);
		
		PapelResponse papelResponse = converter.convert(papelBuscado, PapelResponse.class);
		
		return ResponseEntity.ok(papelResponse);
	}
	
	
	@GetMapping
	public List<PapelResponse> listar(){
		List<Papel> papeis = papelServiceImpl.listar();
		
		List<PapelResponse> listaPapeisResponse = converter.mapList(papeis, PapelResponse.class);
		
		return listaPapeisResponse;
	}
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws PapelNaoEncontradoException {
		papelServiceImpl.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
}
