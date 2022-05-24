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
import com.klok.treinamento.adesoes.api.application.service.CampoServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Campo;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.campo.CampoRequest;
import com.klok.treinamento.adesoes.api.presentation.campo.CampoResponse;

@RestController
@RequestMapping(value = "/campos")
public class CampoController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CampoServiceImpl campoServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	
	@PostMapping
	public ResponseEntity<CampoResponse> cadastrar(@Valid @RequestBody CampoRequest campoRequest, HttpServletResponse response) throws CampoExistenteException {
		
		Campo campo = converter.convert(campoRequest, Campo.class);
		
		campo = campoServiceImpl.cadastrar(campo);
		
		CampoResponse campoResponse = converter.convert(campo, CampoResponse.class);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, campo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(campoResponse);
	}
	
	
	@PutMapping("/{codigo}") 
	public ResponseEntity<CampoResponse> atualizar(@PathVariable Long codigo, @Valid @RequestBody CampoRequest campoRequest) throws CampoNaoEncontradoException, CampoExistenteException{
		
		Campo campo = converter.convert(campoRequest, Campo.class);
		
		campo = campoServiceImpl.atualizar(codigo, campo);
		
		CampoResponse campoResponse = converter.convert(campo, CampoResponse.class);
		
		return ResponseEntity.ok(campoResponse);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<CampoResponse> buscar(@PathVariable Long codigo) throws CampoNaoEncontradoException{
		Campo campoBuscado = campoServiceImpl.buscar(codigo);
		
		CampoResponse campoResponse = converter.convert(campoBuscado, CampoResponse.class);
		
		return ResponseEntity.ok(campoResponse);
	}
	
	
	@GetMapping
	public List<CampoResponse> listar(){
		List<Campo> campos = campoServiceImpl.listar();
		
		List<CampoResponse> listaCamposResponse = converter.mapList(campos, CampoResponse.class);
		
		return listaCamposResponse;
	}
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws CampoNaoEncontradoException{
		campoServiceImpl.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
}
